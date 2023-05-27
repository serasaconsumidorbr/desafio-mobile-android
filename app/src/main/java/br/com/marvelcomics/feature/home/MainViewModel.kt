package br.com.marvelcomics.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marvelcomics.base.util.Resource
import br.com.marvelcomics.base.util.UiException
import br.com.marvelcomics.data.repository.MarvelCharRepository
import br.com.marvelcomics.model.MarvelCharacter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val marvelCharRepository: MarvelCharRepository
) : ViewModel() {

    private val _charactersResource = MutableLiveData<Resource<List<MarvelCharacter>>>()

    private val _characters = MediatorLiveData<MutableList<MarvelCharacter>>()
    val characters: LiveData<MutableList<MarvelCharacter>> get() = _characters

    val loading: LiveData<Boolean> = Transformations.map(_charactersResource) {
        it is Resource.Loading
    }

    val error: LiveData<Pair<Boolean,UiException?>> = Transformations.map(_charactersResource) {
        Pair(it is Resource.Error,it.exception)
    }

    init {
        _characters.addSource(_charactersResource) {
            if (it is Resource.Success) {
                it.data?.let { data ->
                    val currentList = _characters.value ?: mutableListOf()
                    currentList.addAll(data)
                    _characters.postValue(currentList)
                }
            }
        }
        fetchMarvelChars()
    }

    fun fetchMarvelChars() {
        viewModelScope.launch {
            marvelCharRepository.fetchCharacters(pageSize()).collectLatest {
                _charactersResource.postValue(it)
            }
        }
    }

    fun isInitialFetch(): Boolean = pageSize() == 0

    private fun pageSize() = _characters.value?.size ?: 0

}