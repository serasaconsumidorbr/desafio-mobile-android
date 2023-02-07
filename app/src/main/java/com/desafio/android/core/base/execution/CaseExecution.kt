package com.desafio.android.core.base.execution

import androidx.compose.runtime.mutableStateOf

class CaseExecution(
    initState: CaseExecutionState = CaseExecutionState.NotLoading
) {
    private var state = mutableStateOf(initState)

    private fun setState(state: CaseExecutionState) {
        this.state.value = state
    }

    fun setAsLoading() = setState(state = CaseExecutionState.Loading)
    fun setAsNotLoading() = setState(state = CaseExecutionState.NotLoading)

    fun isLoading() = state.value is CaseExecutionState.Loading
}

sealed class CaseExecutionState {
    object Loading : CaseExecutionState()
    object NotLoading : CaseExecutionState()
}