package com.example.home_data.di

import com.example.home_data.remote.HomeListApi
import com.example.home_data.remote.datasource.HomeListDataSource
import com.example.home_data.remote.datasource.HomeListDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
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

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeDataModuleBinder {

    @Binds
    @Singleton
    abstract fun bindsHomeListDataSource(
        homeListDataSourceImpl: HomeListDataSourceImpl,
    ): HomeListDataSource
}