package com.example.home_data.di

import com.example.home_data.remote.HomeListRepositoryImpl
import com.example.home_domain.repository.HomeListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeListRepository(
        homeListRepository: HomeListRepositoryImpl,
    ): HomeListRepository
    
}
