package com.desafio.android.core.base.execution

sealed class CaseResult<T> {
    data class Success<T>(val result: T) : CaseResult<T>()
    data class Failure<T>(val throwable: Throwable) : CaseResult<T>()
}