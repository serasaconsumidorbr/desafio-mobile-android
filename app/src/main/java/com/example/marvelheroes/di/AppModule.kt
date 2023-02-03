package com.example.marvelheroes.di

import com.example.marvelheroes.data.datasource.CharactersRemoteDataSource
import com.example.marvelheroes.data.dto.ApiResponse
import com.example.marvelheroes.data.repositories.CharactersRepository
import com.example.marvelheroes.domain.repositories.ICharactersRepository
import com.example.marvelheroes.domain.services.HeroesService
import com.example.marvelheroes.presentation.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCharactersRepository(
        remoteDataSource: CharactersRemoteDataSource
    ) = CharactersRepository(remoteDataSource)

    @Singleton
    @Provides
    fun providesHeroesService(
        repository: CharactersRepository
    ) = HeroesService(repository)

    @Singleton
    @Provides
    fun providesHomeViewModel(
        service: HeroesService
    ) = HomeViewModel(service)


}