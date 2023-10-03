package com.marvelverse.data

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=${exception.message}]"
            Loading -> "Loading"
        }
    }

    val isLoading
        get() = this is Loading

    val succeeded
        get() = this is Success && data != null
}