package com.example.home_presentation

import com.example.home_presentation.screen.HomeScreenUiEvent
import com.example.view_model.BaseViewModel

abstract class HomeBaseViewModel<STATE>(
    firstState: STATE,
) : BaseViewModel<HomeScreenUiEvent, STATE>(firstState) {

    override fun dispatchEvent(event: HomeScreenUiEvent) {
        when (event) {
            is HomeScreenUiEvent.RetryLoad -> getCharacters()
            is HomeScreenUiEvent.CardClick -> event.openDetailsScreenAction()
        }
    }

    abstract fun getCharacters()
}