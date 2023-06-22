package com.example.marvel_characters.core.di

import com.example.marvel_characters.core.network.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.MessageDigest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Singleton
    @Provides
    fun providesRetrofit(): MarvelService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val original: Request = chain.request()
                        val originalHttpUrl: HttpUrl = original.url()
                        val ts = System.currentTimeMillis().toString()
                        val hash =
                            "$ts$PRIVATE_KEY$PUBLIC_KEY".toMD5()
                        val url = originalHttpUrl.newBuilder()
                            .addQueryParameter(API_KEY_QUERY, PUBLIC_KEY)
                            .addQueryParameter(HASH_QUERY, hash)
                            .addQueryParameter(TIMESTAMP_QUERY, ts)
                            .build()
                        val requestBuilder: Request.Builder = original.newBuilder()
                            .url(url)
                        val request: Request = requestBuilder.build()
                        chain.proceed(request)
                    }
                    .build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MarvelService::class.java)

    companion object {
        private fun String.toMD5(): String {
            val bytes = MessageDigest.getInstance(ALGORITHM_TYPE).digest(this.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }

        private const val BASE_URL = "https://gateway.marvel.com:443/"
        //TODO add private key here
        private const val PRIVATE_KEY = ""
        //TODO add public key here
        private const val PUBLIC_KEY = ""
        private const val ALGORITHM_TYPE = "MD5"
        private const val API_KEY_QUERY = "apikey"
        private const val HASH_QUERY = "hash"
        private const val TIMESTAMP_QUERY = "ts"
    }
}