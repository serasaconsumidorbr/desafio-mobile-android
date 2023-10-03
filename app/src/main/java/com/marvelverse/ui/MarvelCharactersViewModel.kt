package com.marvelverse.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import com.marvelverse.data.Result
import com.marvelverse.data.Result.Success
import com.marvelverse.domain.MarvelCharacter
import com.marvelverse.domain.MarvelCharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelCharactersViewModel @Inject constructor(private val marvelCharactersRepository: MarvelCharactersRepository) :
    ViewModel() {

    val characters: LiveData<MutableList<MarvelCharacter>> =
        marvelCharactersRepository.marvelCharacters.switchMap { marvelCharactersResult ->
            insertFetchedCharactersFrom(marvelCharactersResult)
        }

    val selectedCharacter: MutableLiveData<MarvelCharacter> = MutableLiveData<MarvelCharacter>()

    val totalCharacters = marvelCharactersRepository.totalCharacters

    val favoriteCharacters: LiveData<MutableList<MarvelCharacter>> =
        marvelCharactersRepository.favoriteCharacters.map { marvelCharactersResult ->
            marvelCharactersResult as MutableList<MarvelCharacter>
        }

    private val _isSelectedCharacterAFavoriteCharacter: MutableLiveData<Boolean> =
        selectedCharacter.switchMap {
            if (favoriteCharacters.value != null) MutableLiveData(favoriteCharacters.value!!.contains(
                it)) else MutableLiveData(false)
        } as MutableLiveData<Boolean>
    val isSelectedCharacterAFavoriteCharacter: LiveData<Boolean> =
        _isSelectedCharacterAFavoriteCharacter

    fun loadCharacters(name: String? = null) {
        viewModelScope.launch {
            val numberOfLoadedCharacters = characters.value?.size ?: 0
            marvelCharactersRepository.retrieveCharactersFrom(name, numberOfLoadedCharacters)
        }
    }

    fun removeCharacters() {
        characters.value?.clear()
    }

    fun setSelected(marvelCharacter: MarvelCharacter) {
        selectedCharacter.postValue(marvelCharacter)
    }

    private val _isLoadingCharacters: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoadingCharacters: LiveData<Boolean> = _isLoadingCharacters

    private val _isErrorLoadingCharacters: MutableLiveData<Boolean> = MutableLiveData(false)
    val isErrorLoadingCharacters: LiveData<Boolean> = _isErrorLoadingCharacters

    private fun insertFetchedCharactersFrom(marvelCharactersResult: Result<List<MarvelCharacter>>): LiveData<MutableList<MarvelCharacter>> {
        val marvelCharacters = mutableListOf<MarvelCharacter>()
        this.characters.value?.let { marvelCharacters.addAll(it) }
        getCharactersDataFrom(marvelCharactersResult).value?.let { marvelCharacters.addAll(it) }
        return MutableLiveData(marvelCharacters)
    }

    private fun getCharactersDataFrom(marvelCharactersResult: Result<List<MarvelCharacter>>?): LiveData<List<MarvelCharacter>> {
        val result = MutableLiveData<List<MarvelCharacter>>()
        when (marvelCharactersResult) {
            is Success -> {
                marvelCharactersResult.data.also { marvelCharacters ->
                    result.value = marvelCharacters
                }
                _isLoadingCharacters.postValue(false)
                _isErrorLoadingCharacters.postValue(false)
            }
            is Result.Error -> {
                result.value = emptyList()
                _isLoadingCharacters.postValue(false)
                _isErrorLoadingCharacters.postValue(true)
            }
            is Result.Loading -> {
                _isErrorLoadingCharacters.postValue(false)
                _isLoadingCharacters.postValue(true)
            }
        }

        return result
    }

    fun toggleFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            val marvelCharacter = selectedCharacter.value!!
            if (marvelCharactersRepository.isFavorite(marvelCharacter)) {
                marvelCharactersRepository.removeFromFavorites(marvelCharacter)
            } else {
                marvelCharactersRepository.addToFavorites(marvelCharacter)
            }
        }
    }

    fun toggleFabIcon() {
        _isSelectedCharacterAFavoriteCharacter.value =
            !_isSelectedCharacterAFavoriteCharacter.value!!
    }
}