package com.example.home_data.di

import com.example.home_data.remote.HomeListRepositoryImpl
import com.example.home_data.remote.datasource.HomeListDataSource
import com.example.home_data.remote.datasource.HomePageConfig
import com.example.home_domain.repository.HomeListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {

    @Provides
    @Singleton
    fun providesHomeListRepository(
        homeListDataSource: HomeListDataSource,
        homePageConfig: HomePageConfig
    ): HomeListRepository = HomeListRepositoryImpl(
        homeListDataSource = homeListDataSource,
        pageSize = homePageConfig.size
    )

}
