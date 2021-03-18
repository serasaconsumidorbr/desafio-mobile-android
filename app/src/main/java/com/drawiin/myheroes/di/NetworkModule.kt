package com.drawiin.myheroes.di

import com.drawiin.myheroes.network.service.HeroesService
import com.drawiin.myheroes.utils.NAMED_API_KEY
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesHeroesService(): HeroesService {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(HeroesService::class.java)
    }

    @Singleton
    @Provides
    @Named(NAMED_API_KEY)
    fun providesApiKey(): String {
        return "6971ee8cf8ffbe03d29e6e85e9121238"
    }
}