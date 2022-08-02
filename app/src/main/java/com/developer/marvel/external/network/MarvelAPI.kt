package com.developer.marvel.external.network

import com.developer.marvel.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object MarvelAPI {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(initOkHttpClient())
            .build()
    }

    private fun initOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(loggingInterceptor())
        }

        client.addInterceptor(hashInterceptor())

        return client.build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

    private fun hashInterceptor(): Interceptor =
        Interceptor {

            val accessToken = Authentication.generateAccessToken()

            val url: HttpUrl = it.request().url.newBuilder()
                .addQueryParameter("ts", accessToken.timestamp.toString())
                .addQueryParameter("apikey", accessToken.publicKey)
                .addQueryParameter("hash", accessToken.token)
                .build()

            val requestBuilder = it.request().newBuilder().url(url)
            val request = requestBuilder.build()

            it.proceed(request)
        }
}