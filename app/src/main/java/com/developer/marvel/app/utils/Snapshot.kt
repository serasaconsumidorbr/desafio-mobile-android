package com.developer.marvel.app.utils

sealed class Snapshot<out T> {
    class Loading<out T> : Snapshot<T>()

    //class Idle<out T> : Snapshot<T>()
    data class Success<out T>(val data: T) : Snapshot<T>()
    data class Error<out T>(val code: Int, val message: String?) : Snapshot<T>()
    data class Failure<out T>(val exception: Exception) : Snapshot<T>()
}