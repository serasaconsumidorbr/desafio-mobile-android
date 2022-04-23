package com.br.leandro.marvel_hero_app.ui.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.fernandohbrasil.marvelsquad.datasource.MarvelRepository
import com.fernandohbrasil.marvelsquad.datasource.db.model.CharacterEntity

import com.fernandohbrasil.marvelsquad.datasource.network.model.Character
import com.fernandohbrasil.marvelsquad.ui.paging.CharactersDataSourceFactory
import com.fernandohbrasil.marvelsquad.ui.viewmodel.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val pageSize = 20

class RootViewModel(private val repository: MarvelRepository) : BaseViewModel() {

    private val _rootCharactersApiState = MutableLiveData<RootCharactersApiState>()
    val rootCharactersApiState: LiveData<RootCharactersApiState>
        get() = _rootCharactersApiState

    private val _rootMySquadState = MutableLiveData<RootMySquadState>()
    val rootMySquadState: LiveData<RootMySquadState>
        get() = _rootMySquadState

    private var characterList: Flowable<PagedList<Character>>

    private val sourceFactory: CharactersDataSourceFactory =
        CharactersDataSourceFactory(repository, disposable)

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setPrefetchDistance(pageSize)
            .setEnablePlaceholders(false)
            .build()

        characterList = RxPagedListBuilder(sourceFactory, config)
            .buildFlowable(BackpressureStrategy.BUFFER)
            .cache()
    }

    fun start() {
        _rootMySquadState.postValue(RootMySquadStarted)

        disposable.add(characterList
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _rootCharactersApiState.postValue(RootCharactersApiSuccess(it)) },
                { _rootCharactersApiState.postValue(RootCharactersApiError(it.message)) }
            )
        )
    }

    fun findMySquad() {
        disposable.add(
            repository.getMySquad()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _rootMySquadState.postValue(RootMySquadSuccess(it) ) },
                    { _rootMySquadState.postValue(RootMySquadError(it.message)) }
                )
        )
    }

    fun finish() {
        _rootCharactersApiState.postValue(RootCharactersApiFinished)
        _rootMySquadState.postValue(RootMySquadFinished)
    }
}

sealed class RootMySquadState
object RootMySquadStarted : RootMySquadState()
object RootMySquadFinished : RootMySquadState()
data class RootMySquadSuccess(val charactersEntity: MutableList<CharacterEntity>) : RootMySquadState()
data class RootMySquadError(val error: String?) : RootMySquadState()

sealed class RootCharactersApiState
object RootCharactersApiFinished : RootCharactersApiState()
data class RootCharactersApiSuccess(val characters: PagedList<Character>) : RootCharactersApiState()
data class RootCharactersApiError(val error: String?) : RootCharactersApiState()