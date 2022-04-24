package dev.ribeiro.bruno.desafioandroid.core


sealed class State<out T: Any>{

    data class Success<out T: Any>(val result: T): State<T>()

    data class Error(val result: Throwable): State<Nothing>()

}
