package com.example.view_model.observable.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class ObservableFlow<T>(first: T): Observable<T> {
    private val _uiState = MutableStateFlow(first)
    val uiState: StateFlow<T> = _uiState

    override fun update(updateBlock: (current: T) -> T) {
        _uiState.value = updateBlock(uiState.value)
    }
}