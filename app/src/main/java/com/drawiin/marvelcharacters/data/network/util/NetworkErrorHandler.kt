package com.drawiin.marvelcharacters.data.network.util

import com.drawiin.marvelcharacters.data.network.model.error.ApiError
import com.drawiin.marvelcharacters.data.network.model.error.NetworkError
import com.google.gson.Gson
import kotlinx.coroutines.coroutineScope
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class NetworkErrorHandler {
    protected suspend fun <T> requestScope(block: suspend () -> Response<T>): T? {
        return coroutineScope {
            try {
                block().run {
                    if (isSuccessful) {
                        body()
                    } else {
                        val errorMessage = getErrorMessage(errorBody())
                        throw NetworkError.HttpError(errorMessage)
                    }
                }
            } catch (error: Exception) {
                throw when (error) {
                    is NetworkError.HttpError -> error
                    is SocketTimeoutException -> NetworkError.TimeoutError()
                    is UnknownHostException -> NetworkError.UnknownHostError()
                    is IOException -> NetworkError.IOError()
                    else -> NetworkError.UnexpectedError(error)
                }
            }
        }
    }

    private fun getErrorMessage(errorBody: ResponseBody? = null): String? {
        val error = deserialize(errorBody)
        return error?.status ?: error?.message
    }

    private fun deserialize(responseBody: ResponseBody?): ApiError? {
        return Gson().fromJson(
            responseBody?.string(),
            ApiError::class.java
        )
    }
}