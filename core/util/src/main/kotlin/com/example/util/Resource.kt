package com.example.util

sealed interface Resource<T> {
    data class Success<T>(val data: T): Resource<T>
    data class Error<T>(val message: String): Resource<T>
    class Loading<T>: Resource<T>
}