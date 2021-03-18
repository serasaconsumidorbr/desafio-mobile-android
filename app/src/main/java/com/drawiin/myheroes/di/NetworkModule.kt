package com.drawiin.myheroes.di

import com.drawiin.myheroes.network.service.HeroesService
import com.drawiin.myheroes.utils.NAMED_API_KEY
import com.drawiin.myheroes.utils.NAMED_HASH
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesLoggingService(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Singleton
    @Provides
    fun providesHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()
    }


    @Singleton
    @Provides
    fun providesHeroesService(client: OkHttpClient): HeroesService {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(HeroesService::class.java)
    }

    @Singleton
    @Provides
    @Named(NAMED_API_KEY)
    fun providesApiKey(): String {
        return "6971ee8cf8ffbe03d29e6e85e9121238"
    }

    @Singleton
    @Provides
    @Named(NAMED_HASH)
    fun providesHash(): String {
        return "d1cfbf3c64990b5c5c152d39e7f045321a43f480"
    }
}