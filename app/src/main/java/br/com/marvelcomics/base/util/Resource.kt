package br.com.marvelcomics.base.util

sealed class Resource<T>(var data: T? = null, var exception: UiException? = null) {

    class Success<T>(data: T) : Resource<T>(data = data)
    class Loading<T> : Resource<T>()
    class Error<T>(exception: UiException) : Resource<T>(exception = exception)
}