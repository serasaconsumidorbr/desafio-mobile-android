package com.developer.marvel.app.utils

sealed class Snapshot<out T> {
    class Loading<out T> : Snapshot<T>()
    //class Idle<out T> : Snapshot<T>()
    data class Success<out T>(val data: T) : Snapshot<T>()
    data class Failure<out T>(val throwable: Throwable) : Snapshot<T>()
}