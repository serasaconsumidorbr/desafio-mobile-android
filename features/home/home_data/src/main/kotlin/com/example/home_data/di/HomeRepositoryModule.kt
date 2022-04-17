package com.example.home_data.di

import com.example.home_data.remote.mapper.CharacterDtoToCharacterMapper
import com.example.home_data.remote.mapper.impl.CharacterDtoToCharacter
import com.example.home_data.remote.datasource.HomeListDataSourceImpl
import com.example.home_data.remote.HomeListRepositoryImpl
import com.example.home_data.remote.datasource.HomeListDataSource
import com.example.home_domain.repository.HomeListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharacterDtoToCharacterMapper(
        characterDtoToCharacter: CharacterDtoToCharacter,
    ): CharacterDtoToCharacterMapper

    @Binds
    @Singleton
    abstract fun bindHomeListDataSource(
        homeListDataSource: HomeListDataSourceImpl,
    ): HomeListDataSource

    @Binds
    @Singleton
    abstract fun bindHomeListRepository(
        homeListRepository: HomeListRepositoryImpl,
    ): HomeListRepository
}