package br.com.marvelcomics.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marvelcomics.base.util.Resource
import br.com.marvelcomics.data.repository.MarvelCharRepository
import br.com.marvelcomics.model.MarvelCharacter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val marvelCharRepository: MarvelCharRepository
) : ViewModel() {

    private val _charactersResource = MutableLiveData<Resource<List<MarvelCharacter>>>()

    private val _featureCharacters = MediatorLiveData<List<MarvelCharacter>>()
    val featureCharacters: LiveData<List<MarvelCharacter>> get() = _featureCharacters

    private val _characters = MediatorLiveData<MutableList<MarvelCharacter>>()
    val characters: LiveData<MutableList<MarvelCharacter>> get() = _characters

    val loading: LiveData<Boolean> = Transformations.map(_charactersResource) {
        it is Resource.Loading
    }

    val error: LiveData<Boolean> = Transformations.map(_charactersResource) {
        it is Resource.Error
    }

    init {
        _featureCharacters.addSource(_charactersResource) {
            if (_featureCharacters.value?.isNotEmpty() == true) {
                return@addSource
            }
            if (it is Resource.Success) {
                _featureCharacters.postValue(it.data?.take(5))
            }
        }

        _characters.addSource(_charactersResource) {
            val data = it.data ?: emptyList()
            if (it is Resource.Success) {
                if (_characters.value.isNullOrEmpty()) {
                    _characters.postValue(data.drop(5).toMutableList())
                } else {
                    val characters = _characters.value
                    characters?.addAll(data)
                    _characters.postValue(characters ?: mutableListOf())
                }
            }
        }
    }

    fun fetchMarvelChars() {
        viewModelScope.launch {
            marvelCharRepository.fetchCharacters(getCurrentOffset()).collectLatest {
                _charactersResource.postValue(it)
            }
        }
    }

    private fun getCurrentOffset(): Int {
        val featureSize = _featureCharacters.value?.size ?: 0
        val charSize = _characters.value?.size ?: 0
        return featureSize + charSize
    }

    fun isInitialFetch(): Boolean =
        _characters.value.isNullOrEmpty() && _featureCharacters.value.isNullOrEmpty()

}