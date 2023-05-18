package br.com.marvelcomics.base.resource

sealed class Resource<T>(var data: T? = null, exception: Exception? = null) {

    class Success<T>(data: T) : Resource<T>(data = data)
    class Loading<T> : Resource<T>()
    class Error<T>(exception: Exception) : Resource<T>(exception = exception)
}