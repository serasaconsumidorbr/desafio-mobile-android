package com.example.marvel_app.di

import com.example.marvel_app.framework.di.qualifier.BaseUrl
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@BaseUrl
@InstallIn(SingletonComponent::class)
object BaseUrlTestModule {

    @BaseUrl
    @Provides
    fun providesBaseUrl(): String = "http://localhost:8080/"
}