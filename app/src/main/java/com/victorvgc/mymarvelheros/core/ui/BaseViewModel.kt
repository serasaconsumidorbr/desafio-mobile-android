package com.victorvgc.mymarvelheros.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<T> : ViewModel() {
    protected val stateFlow = MutableStateFlow<UIState<T>>(UIState.Initial())

    val uiState: StateFlow<UIState<T>> = stateFlow

}