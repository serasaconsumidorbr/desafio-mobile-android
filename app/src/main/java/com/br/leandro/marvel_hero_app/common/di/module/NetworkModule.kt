package com.br.leandro.marvel_hero_app.common.di.module

import dagger.Module
import dagger.Provides
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.MessageDigest
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
object NetworkModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofit(okHttpClient: OkHttpClient): MarvelService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MarvelService::class.java)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(ApiInterceptor())
            .build()
    }

    private class ApiInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val urlBuilder = request.url().newBuilder()

            val currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()).toString()
            val privateApiKey = BuildConfig.API_PRIVATE
            val publicApiKey = BuildConfig.API_PUBLIC
            val hash = generateHash(currentTime, privateApiKey, publicApiKey)
            val newUrl = urlBuilder
                .addQueryParameter("apikey", publicApiKey)
                .addQueryParameter("hash", hash)
                .addQueryParameter("ts", currentTime)
                .build()

            val newRequest = request.buildNewUrl(newUrl)

            return chain.proceed(newRequest)
        }

        private fun Request.buildNewUrl(newUrl: HttpUrl): Request {
            return this.newBuilder().url(newUrl).build()
        }

        private fun String.toMD5(): String {
            val md = MessageDigest.getInstance("MD5")
            val digested = md.digest(toByteArray())
            return digested.joinToString("") { String.format("%02x", it) }
        }

        private fun generateHash(
            currentTime: String,
            privateApiKey: String,
            publicApiKey: String
        ): String {
            return (currentTime.toString() + privateApiKey + publicApiKey).toMD5()
        }
    }
}