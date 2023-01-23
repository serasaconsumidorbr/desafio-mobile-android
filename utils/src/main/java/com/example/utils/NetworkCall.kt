package com.example.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

typealias NetworkAPIInvoke<T> = suspend () -> Response<T>



suspend fun <T : Any> networkCall(
    networkApiCall: NetworkAPIInvoke<T>
): Flow<T> {
    return flow {
        val response = networkApiCall()
        if (response.isSuccessful)
            response.body()?.let { emit(it) }
                ?: throw IOException("API call successful but empty response body")



        return@flow
    }.retryWhen { cause, attempt ->
        attempt < 3 && (cause is SocketTimeoutException || cause is UnknownHostException)
    }.catch { e ->
        throw Exception()
    }
}