package br.com.maceda.marvel.repository

import java.lang.Exception

sealed class ResponseRepositoryResult<out T>{
    class Success<out T>(val value: T): ResponseRepositoryResult<T>()
    class Error(val message: String) : ResponseRepositoryResult<Nothing>()
    class ErrorException(val e: Exception) : ResponseRepositoryResult<Nothing>()
}
