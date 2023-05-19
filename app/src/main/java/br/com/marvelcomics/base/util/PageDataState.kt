package br.com.marvelcomics.base.util

sealed interface PageDataState<T> {
    class Data<T>(val data: T) : PageDataState<T>
    class Loading<T> : PageDataState<T>
    class Error<T> : PageDataState<T>
}