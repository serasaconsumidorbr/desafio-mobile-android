package com.example.marvelheroes.data.extensions

import com.example.marvelheroes.data.retrofit.ErrorModel
import retrofit2.HttpException
import timber.log.Timber
import java.net.HttpURLConnection

fun Throwable.asErrorObjectByJson(): ErrorModel? =
    safeHeritage<HttpException>()?.let { httpException ->
        val errorResponse = httpException.response()?.cloneErrorResponseBody()?.charStream()
        val errorCode = httpException.code()
        errorResponse?.let {
            try {
                if (errorCode in HttpURLConnection.HTTP_BAD_REQUEST..HttpURLConnection.HTTP_INTERNAL_ERROR) {
                    errorResponse.fromJsonOrNull()
                } else {
                    Timber.i("HttpExceptionObjectByJson Invalid return http$errorCode")
                    null
                }
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
        }
    }