package com.victorvgc.mymarvelheros.core.utils

import com.victorvgc.mymarvelheros.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT: Long = 30

fun <Type> getApi(serviceClass: Class<Type>): Type {

    val loggingInterceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
    client.readTimeout(TIMEOUT, TimeUnit.SECONDS)
    client.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    client.addInterceptor(loggingInterceptor)


    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create())
    return retrofit.build().create(serviceClass)
}