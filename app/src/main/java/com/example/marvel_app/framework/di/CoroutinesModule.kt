package com.example.marvel_app.framework.di

import com.example.core.utils.AppCoroutinesDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {

    @Provides
    fun provideDispatchers() = AppCoroutinesDispatchers (
        Dispatchers.IO,
        Dispatchers.Default,
        Dispatchers.Main,
    )
}