package com.example.marvel_app.framework.di

import com.example.marvel_app.BuildConfig.BASE_URL
import com.example.marvel_app.framework.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @BaseUrl
    @Provides
    fun providesBaseUrl(): String = BASE_URL
}