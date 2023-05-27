package com.victorvgc.mymarvelheros.core.ui

import com.victorvgc.mymarvelheros.core.errors.Failure

sealed class UIState<T> {
    data class Initial<T>(val preFetchData: T? = null) : UIState<T>()
    data class Loading<T>(val preFetchData: T? = null) : UIState<T>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error<T>(val errorData: T? = null, val failure: Failure) : UIState<T>()
}