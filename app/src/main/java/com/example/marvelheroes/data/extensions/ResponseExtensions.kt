package com.example.marvelheroes.data.extensions

import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.asResponseBody
import retrofit2.Response

fun Response<*>.cloneErrorResponseBody(): ResponseBody? {
    val responseBody: ResponseBody? = errorBody()
    val bufferedSource = responseBody?.source()
    bufferedSource?.request(Long.MAX_VALUE)
    return safeLet(responseBody?.contentType(), bufferedSource?.buffer?.clone(), responseBody?.contentLength()) { contentType, bufferClone, length ->
        bufferClone.asResponseBody(contentType, length)
    }
}