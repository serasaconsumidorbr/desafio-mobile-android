package com.br.leandro.marvel_hero_app.ui.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.EmptyResultSetException
import com.br.leandro.marvel_hero_app.datasource.MarvelRepository
import com.br.leandro.marvel_hero_app.datasource.network.model.Character
import com.br.leandro.marvel_hero_app.datasource.network.model.Comics
import com.br.leandro.marvel_hero_app.ui.viewmodel.BaseViewModel
import com.br.leandro.marvel_hero_app.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val repository: MarvelRepository) : BaseViewModel() {

    private val _characterStateApi = MutableLiveData<CharacterStateApi>()
    val characterStateApi: LiveData<CharacterStateApi>
        get() = _characterStateApi

    private val _comicsStateApi = MutableLiveData<ComicsStateApi>()
    val comicsStateApi: LiveData<ComicsStateApi>
        get() = _comicsStateApi

    private val _characterHireStateDb = MutableLiveData<CharacterHireStateDb>()
    val characterHireStateDb: LiveData<CharacterHireStateDb>
        get() = _characterHireStateDb

    fun start() {
        _characterStateApi.postValue(CharacterApiStarted)
        _comicsStateApi.postValue(ComicsApiStarted)
        _characterHireStateDb.postValue(CharacterHireStateDbStarted)
    }

    fun findCharacterApi(characterId: Int) {
        disposable.add(
            repository.getCharacterByIdApi(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _characterStateApi.postValue(CharacterApiSuccess(it.data.results[0])) },
                    { _characterStateApi.postValue(CharacterApiError(it.message)) }
                )
        )
    }

    fun findCharacterDb(characterId: Int) {
        disposable.add(
            repository.getCharacterByIdDatabase(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _characterHireStateDb.postValue(CharacterHireStateDbHired) },
                    {
                        if (it !is EmptyResultSetException) {
                            _characterHireStateDb.postValue(CharacterHireStateDbError(it.message))
                        }
                    }
                )
        )
    }

    fun findComics(characterId: Int) {
        disposable.add(
            repository.getComicsByCharacterIdApi(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _comicsStateApi.postValue(ComicsApiSuccess(it)) },
                    { _comicsStateApi.postValue(ComicsApiError(it.message)) }
                )
        )
    }

    fun hireOrFireCharacter() {
        val character = getCurrentCharacter()

        if (character != null) {
            if (_characterHireStateDb.value is CharacterHireStateDbHired) {
                fireCharacter(character)
            } else {
                hireCharacter(character)
            }
        }
    }

    private fun fireCharacter(character: Character) {
        disposable.add(
            repository.fireCharacter(character.toCharacterEntity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _characterHireStateDb.postValue(CharacterHireStateDbFired) },
                    { _characterHireStateDb.postValue(CharacterHireStateDbError(it.message)) }
                )
        )
    }

    private fun hireCharacter(character: Character) {
        disposable.add(
            repository.hireCharacter(character.toCharacterEntity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _characterHireStateDb.postValue(CharacterHireStateDbHired) },
                    { _characterHireStateDb.postValue(CharacterHireStateDbError(it.message)) }
                )
        )
    }

    private fun getCurrentCharacter(): Character? {
        if (_characterStateApi.value is CharacterApiSuccess) {
            return (_characterStateApi.value as CharacterApiSuccess).character
        }
        return null
    }

    fun finish() {
        _characterStateApi.postValue(CharacterApiFinished)
        _comicsStateApi.postValue(ComicsApiFinished)
        _characterHireStateDb.postValue(CharacterHireStateDbFinished)
    }
}

sealed class CharacterHireStateDb
object CharacterHireStateDbStarted : CharacterHireStateDb()
object CharacterHireStateDbFired : CharacterHireStateDb()
object CharacterHireStateDbHired : CharacterHireStateDb()
object CharacterHireStateDbFinished : CharacterHireStateDb()
data class CharacterHireStateDbError(val error: String?) : CharacterHireStateDb()

sealed class CharacterStateApi
object CharacterApiStarted : CharacterStateApi()
object CharacterApiFinished : CharacterStateApi()
data class CharacterApiSuccess(val character: Character) : CharacterStateApi()
data class CharacterApiError(val error: String?) : CharacterStateApi()

sealed class ComicsStateApi
object ComicsApiStarted : ComicsStateApi()
object ComicsApiFinished : ComicsStateApi()
data class ComicsApiSuccess(val comics: Comics) : ComicsStateApi()
data class ComicsApiError(val error: String?) : ComicsStateApi()