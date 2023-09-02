package com.example.marvel_app.framework.di

import com.example.core.utils.AppCoroutinesDispatchers
import com.example.core.utils.CoroutinesDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {

    @Binds
    fun bindDispatchers(dispatchers: AppCoroutinesDispatchers): CoroutinesDispatchers
}