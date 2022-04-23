package com.br.leandro.marvel_hero_app.datasource.network

import com.fernandohbrasil.marvelsquad.extensions.md5
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

private const val BASE_URL = "https://gateway.marvel.com/"
private const val PUBLIC_KEY = "a2f08045db4d373c4566f32e8bc75429"
private const val PRIVATE_KEY = "5e0389e7a799e8b697f6b549f48ad56b858ed80e"

object MarvelAPIFactory {

    fun marvelApi(): MarvelApi {
        val requestInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl: HttpUrl = originalRequest.url

            val timeStamp =
                (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", PUBLIC_KEY)
                .addQueryParameter("hash", "$timeStamp${PRIVATE_KEY}${PUBLIC_KEY}".md5())
                .addQueryParameter("ts", timeStamp)
                .build()

            val requestBuilder: Request.Builder = originalRequest.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val debugInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(debugInterceptor)
        builder.addInterceptor(requestInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(builder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }
}