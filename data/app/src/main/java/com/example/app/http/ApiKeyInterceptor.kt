package com.example.app.http

import com.example.app.BuildConfig
import com.example.utils.HashGenerator
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyInterceptor @Inject constructor() :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val timestamp = System.currentTimeMillis()
        val keysCombine = "$timestamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"

        val md5Hash = HashGenerator().buildMD5Digest(keysCombine)

        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder()
            .addQueryParameter("ts", timestamp.toString())
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter("hash", md5Hash)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}