package com.example.home_data.di

import com.example.home_data.remote.HomeListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDataModule {
    @Provides
    @Singleton
    fun provideStockApi(): HomeListApi = Retrofit.Builder()
        .baseUrl(HomeListApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create()
}