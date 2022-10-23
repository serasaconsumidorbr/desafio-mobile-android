package com.example.home_presentation.components.carousel

import androidx.lifecycle.viewModelScope
import com.example.home_domain.mapper.ResultHomeCarouselToUiStateMapper
import com.example.home_domain.model.HomeCarouselUiState
import com.example.home_domain.usecase.GetHomeCarouselUseCase
import com.example.home_presentation.HomeBaseViewModel
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeCarouselViewModel @Inject constructor(
    private val getHomeCarousel: GetHomeCarouselUseCase,
    private val resultHomeCarouselToUiState: ResultHomeCarouselToUiStateMapper
) : HomeBaseViewModel<HomeCarouselUiState>(HomeCarouselUiState.Loading) {

    init {
        getCharacters()
    }

    override fun getCharacters() {
        getHomeCarousel().onEach { result ->
            updateUiState { resultHomeCarouselToUiState.map(result) }
        }.launchIn(viewModelScope)
    }
}
