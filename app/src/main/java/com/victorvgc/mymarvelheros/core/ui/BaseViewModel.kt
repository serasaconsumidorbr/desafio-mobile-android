package com.victorvgc.mymarvelheros.core.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<T> {
    protected val stateFlow = MutableStateFlow<UIState<T>>(UIState.Initial())

    val uiState: StateFlow<UIState<T>> = stateFlow

}