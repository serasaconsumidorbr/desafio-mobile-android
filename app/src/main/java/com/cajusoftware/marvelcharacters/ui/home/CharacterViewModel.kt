package com.cajusoftware.marvelcharacters.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cajusoftware.marvelcharacters.data.domain.CarouselCharacter
import com.cajusoftware.marvelcharacters.data.network.NoConnectivityException
import com.cajusoftware.marvelcharacters.data.repositories.CharacterRepository
import com.cajusoftware.marvelcharacters.utils.NetworkUtils.exceptionHandler
import com.cajusoftware.marvelcharacters.utils.RetryCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    private var currentJob: Job? = null

    private val _upperCarouselItems = MutableLiveData<List<CarouselCharacter>>()
    val upperCarouselItems: LiveData<List<CarouselCharacter>>
        get() = _upperCarouselItems

    private val _carouselItems = MutableLiveData<PagingData<CarouselCharacter>>()
    val carouselItems: LiveData<PagingData<CarouselCharacter>>
        get() = _carouselItems

    private val _shouldShowPlaceholder = MutableLiveData(true)
    val shouldShowPlaceholder: LiveData<Boolean>
        get() = _shouldShowPlaceholder


    val scope: CoroutineScope
        get() = viewModelScope

    fun getCharactersToUpperCarousel() {
        viewModelScope.launch {
            characterRepository.upperCarouselCharacters.collectLatest {
                _upperCarouselItems.postValue(it)
            }
        }
    }

    fun getCharacters() {
        currentJob?.cancel()
        currentJob = viewModelScope.launch {
            characterRepository.carouselCharacters
                .cancellable()
                .cachedIn(viewModelScope)
                .collectLatest {
                    _carouselItems.postValue(it)
                    characterRepository.getCharactersRandomly()
                }
        }
    }

    fun checkLoadState(loadState: CombinedLoadStates) {
        loadState.apply {
            (prepend as? LoadState.Error)?.error?.apply {
                (this as? NoConnectivityException)?.let {
                    exceptionHandler.handleException(
                        scope.coroutineContext + RetryCallback { getCharacters() },
                        it
                    )
                }
            }
        }
    }
}