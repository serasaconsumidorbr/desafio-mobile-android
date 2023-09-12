package com.example.marvel_characters

sealed class Result<out R>  {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

val Result<*>.succeeded   //TODO treat the possibility of a result be Success but with data == null
    get() = this is Result.Success && data != null