package com.example.home_presentation.screen

sealed interface HomeScreenUiEvent {
    object RetryLoad: HomeScreenUiEvent
    data class CardClick(val openDetailsScreenAction: () -> Unit): HomeScreenUiEvent
}