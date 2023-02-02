package com.example.marvelheroes.data.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections
import java.util.concurrent.TimeUnit

class ServiceGenerator {
    companion object {

        fun <S> createServiceCoroutine(serviceClass: Class<S>, interceptors: List<Interceptor>? = null, url: String): S {

            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())

            val httpClient = OkHttpClient.Builder()

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpClient.protocols(Collections.singletonList(Protocol.HTTP_1_1))
            httpClient.addInterceptor(
                httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                })
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .pingInterval(3, TimeUnit.SECONDS)

            interceptors?.let {
                for (interceptor in interceptors) {
                    httpClient.addInterceptor(interceptor)
                }
            }
            retrofit.client(httpClient.build())
            return retrofit.build().create(serviceClass)
        }
    }
}