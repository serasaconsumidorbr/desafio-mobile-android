package com.drawiin.marvelcharacters.data.network.model.error

sealed class NetworkError(
    val errorMessage: String?
) : Exception() {
    class HttpError(errorMessage: String?) : NetworkError(errorMessage)

    class UnexpectedError(throwable: Throwable) : NetworkError(throwable.message)
    class TimeoutError : NetworkError("Your request is taking too long check your connection")
    class UnknownHostError : NetworkError("Cant connect to the server verify your internet connection")
    class IOError : NetworkError("Could Not Complete the operation")
}