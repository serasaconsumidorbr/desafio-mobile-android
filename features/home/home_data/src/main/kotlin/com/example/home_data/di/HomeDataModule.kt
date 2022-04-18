package com.example.home_data.di

import com.example.home_data.remote.HomeListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDataModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeListApi = retrofit.create(HomeListApi::class.java)
}