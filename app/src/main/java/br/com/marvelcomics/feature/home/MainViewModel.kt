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

    private var pageSize: Int = 0

    private val _characters = MediatorLiveData<List<MarvelCharacter>>()
    val characters: LiveData<List<MarvelCharacter>> get() = _characters

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
                    pageSize += data.size
                    _characters.postValue(data)
                }
            }
        }
        fetchMarvelChars()
    }

    fun fetchMarvelChars() {
        viewModelScope.launch {
            marvelCharRepository.fetchCharacters(pageSize).collectLatest {
                _charactersResource.postValue(it)
            }
        }
    }

    fun isInitialFetch(): Boolean = pageSize == 0

}