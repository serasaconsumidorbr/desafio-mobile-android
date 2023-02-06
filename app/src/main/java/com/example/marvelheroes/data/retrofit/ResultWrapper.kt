package com.example.marvelheroes.data.retrofit

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(
        val code: Int? = null,
        val error: ErrorObject,
        val throwable: Throwable
    ) : ResultWrapper<Nothing>()
}