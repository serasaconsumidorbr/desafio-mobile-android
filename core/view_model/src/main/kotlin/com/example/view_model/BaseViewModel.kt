package com.example.view_model

import androidx.lifecycle.ViewModel
import com.example.view_model.observable.UiStateObservable


abstract class BaseViewModel<EVENT, STATE>(firstState: STATE) : ViewModel() {

    private val stateObservable = UiStateObservable(firstState)
    val uiState = stateObservable.uiState

    abstract fun dispatchEvent(event: EVENT)

    protected fun updateUiState(updateBlock: (currentUiState: STATE) -> STATE) {
        stateObservable.update(updateBlock)
    }
}
