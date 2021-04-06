package com.desafio.marvel.commons.api

interface BaseCallback<T> {

    fun onSuccess(response: T)
    fun onError(error: String)
}