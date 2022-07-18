package com.ncz.desafio_mobile_android.domain.utils

import java.lang.Exception

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    NETWORK_ERROR,
}

data class State<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
    val error: Exception? = null
) {
    companion object {
        fun <T> success(data: T): State<T> = State(status = Status.SUCCESS, data = data)
        fun <T> error(
            message: String? = null,
            errorResponse: Exception? = null
        ): State<T> =
            State(status = Status.ERROR, message = message, error = errorResponse)

        fun <T> networkError(): State<T> = State(status = Status.NETWORK_ERROR)
        fun <T> loading(): State<T> = State(status = Status.LOADING)
    }
}