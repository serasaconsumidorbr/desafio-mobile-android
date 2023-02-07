package com.desafio.android.di

import com.desafio.android.core.base.toast.Toaster
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ToasterModule {
    @Singleton
    @Provides
    fun providesToaster(): Toaster = Toaster()
}