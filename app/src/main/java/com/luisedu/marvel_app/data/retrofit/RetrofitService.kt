package com.luisedu.marvel_app.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

object RetrofitService {

    private const val TIMEOUT_30 = 30

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .readTimeout(TIMEOUT_30.toLong(), TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_30.toLong(), TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_30.toLong(), TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): MarvelApiCall = retrofit.create(MarvelApiCall::class.java)
}