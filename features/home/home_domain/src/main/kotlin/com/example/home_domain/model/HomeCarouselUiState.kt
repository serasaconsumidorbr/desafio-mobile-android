package com.example.home_domain.model

sealed interface HomeCarouselUiState {
    object Loading: HomeCarouselUiState
    object Error: HomeCarouselUiState
    data class Success(val data: List<CharacterHomeUiModel>): HomeCarouselUiState
}
