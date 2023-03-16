package com.cajusoftware.marvelcharacters.data.network.interceptors

import com.cajusoftware.marvelcharacters.BuildConfig
import com.cajusoftware.marvelcharacters.BuildConfig.*
import com.cajusoftware.marvelcharacters.utils.md5
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val timestamp = System.currentTimeMillis().toString()
        val apikey = BuildConfig.API_KEY
        val hashInput = "$timestamp$PRIVATE_API_KEY${BuildConfig.API_KEY}"

        originalRequest.url().newBuilder()
            .addQueryParameter(TIMESTAMP, timestamp)
            .addQueryParameter(API_KEY, apikey)
            .addQueryParameter(HASH, hashInput.md5())
            .addQueryParameter(LIMIT, SERVICE_PAGE_SIZE.toString())
            .build().also {
                val builder = originalRequest.newBuilder().url(it)
                val newRequest = builder.build()
                return chain.proceed(newRequest)
            }
    }

    companion object {
        private const val TIMESTAMP = "ts"
        private const val API_KEY = "apikey"
        private const val HASH = "hash"
        private const val LIMIT = "limit"
    }
}