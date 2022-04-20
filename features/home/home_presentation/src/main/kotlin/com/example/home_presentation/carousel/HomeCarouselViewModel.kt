package com.example.home_presentation.carousel

import androidx.lifecycle.viewModelScope
import com.example.home_domain.repository.HomeCarouselRepository
import com.example.home_presentation.HomeBaseViewModel
import com.example.home_presentation.screen.HomeScreenUiEvent
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeCarouselViewModel @Inject constructor(
    private val repository: HomeCarouselRepository,
) : HomeBaseViewModel<HomeCarouselUiState>(
    HomeCarouselUiState.Loading
) {

    init {
        getCharacters()
    }

    override fun getCharacters() {
        repository
            .getHomeCarouselCharacters()
            .onEach { result ->
                updateUiState {
                    when (result) {
                        is Resource.Success -> result.data.let { characters ->
                            HomeCarouselUiState.Success(data = characters)
                        }
                        is Resource.Error -> HomeCarouselUiState.Error
                        is Resource.Loading -> HomeCarouselUiState.Loading
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}