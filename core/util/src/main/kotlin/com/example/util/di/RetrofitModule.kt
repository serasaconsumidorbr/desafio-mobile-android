package com.example.util.di

import com.example.util.api.Api
import com.example.util.hash.Md5HashGenerator
import com.example.util.hash.impl.Md5HashGeneratorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesHashGenerator(): Md5HashGenerator = Md5HashGeneratorImpl()

    @Provides
    @Singleton
    fun providesClient(hashGenerator: Md5HashGenerator): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor { interceptorChain ->
            val timeStamp = System.currentTimeMillis()
            val requestUrl = interceptorChain
                .request()
                .url
                .newBuilder()
                .addQueryParameter("ts", "$timeStamp")
                .addQueryParameter("apikey", Api.Keys.PUBLIC)
                .addQueryParameter("hash",
                    hashGenerator(
                        timestamp = timeStamp,
                        publicKey = Api.Keys.PUBLIC,
                        privateKey = Api.Keys.PRIVATE
                    )
                )
                .build()
            interceptorChain.proceed(
                interceptorChain.request().newBuilder().url(requestUrl).build()
            )
        }
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
}