package com.example.home_presentation.carousel

import com.example.home_domain.model.CharacterHomeUiModel

sealed interface HomeCarouselUiState {
    object Loading: HomeCarouselUiState
    object Error: HomeCarouselUiState
    data class Success(
        val data: List<CharacterHomeUiModel>
    ): HomeCarouselUiState
}