package com.desafio.marvel.commons.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

class ApiClient {

    private var retrofit: Retrofit

    init {
        retrofit = createRetrofit()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseUrl.MARVEL_STAGE_ENDPOINT)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()
    }

    private fun createHttpClient() = OkHttpClient().newBuilder()
        .addInterceptor { chain ->
            val currentTimestamp = System.currentTimeMillis()
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("ts", currentTimestamp.toString())
                .addQueryParameter("apikey", "051afb17e91fa87ce497119d18630124")
                .addQueryParameter(
                    "hash",
                    (currentTimestamp.toString() + "b5074302ea2487bc12ab6524183aef46ffa7d7e1" + "051afb17e91fa87ce497119d18630124").toMd5()
                )
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .build()

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }

    fun String.toMd5(): String {
        val md = MessageDigest.getInstance("MD5")
        val byteArray = this.toByteArray()
        val bigInt = BigInteger(1, md.digest(byteArray))
        return bigInt.toString(16).padStart(32, '0')
    }

}