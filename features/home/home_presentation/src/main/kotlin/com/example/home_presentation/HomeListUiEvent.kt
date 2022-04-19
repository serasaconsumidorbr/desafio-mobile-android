package com.example.home_presentation

sealed interface HomeListUiEvent {
    object RetryLoad: HomeListUiEvent
}