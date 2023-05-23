package com.example.testeapp.model

data class Result<out T>(val status: Status, val data: T?, val error: Error?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        IN_PROGRESS
    }

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String, error: Error?): Result<T> {
            return Result(Status.ERROR, null, error, message)
        }

        fun <T> inProgress(data: T? = null): Result<T> {
            return Result(Status.IN_PROGRESS, data, null, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$error, message=$message)"
    }
}

data class Error(val status_code: Int = 0,
                 val status_message: String? = null)
