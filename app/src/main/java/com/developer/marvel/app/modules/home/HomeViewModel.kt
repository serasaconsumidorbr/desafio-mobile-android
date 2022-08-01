package com.developer.marvel.app.modules.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.marvel.app.utils.Snapshot
import com.developer.marvel.domain.entities.Character
import com.developer.marvel.domain.usecases.CharacterUseCases
import kotlinx.coroutines.launch

class HomeViewModel(
    private val characterUseCases: CharacterUseCases
): ViewModel() {

    private val limit = 20

    private val _topCharacters: MutableLiveData<Snapshot<List<Character>>> by lazy { MutableLiveData() }
    val topCharacters: LiveData<Snapshot<List<Character>>> get() = _topCharacters

    private val _popularCharacters: MutableLiveData<Snapshot<List<Character>>> by lazy { MutableLiveData() }
    val popularCharacters: LiveData<Snapshot<List<Character>>> get() = _popularCharacters

    fun getCharacters(page: Int = 1) {
        viewModelScope.launch {
            try {
                val topNumber = 5

                val characters = characterUseCases.getCharacters(page, limit)

                if(page == 1)
                    _topCharacters.postValue(Snapshot.Success(characters.subList(0, topNumber)))

                _popularCharacters.postValue(Snapshot.Success(characters.subList(topNumber, characters.size)))

            } catch (e: Exception) {
                _topCharacters.postValue(Snapshot.Failure(e))
            }
        }
    }


}