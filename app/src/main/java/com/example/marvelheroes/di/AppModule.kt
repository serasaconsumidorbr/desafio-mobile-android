package com.example.marvelheroes.di

import com.example.marvelheroes.data.repositories.CharactersRepository
import com.example.marvelheroes.domain.repositories.ICharactersRepository
import com.example.marvelheroes.domain.services.HeroesService
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
    fun provideCharactersRepository() = CharactersRepository()

    @Singleton
    @Provides
    fun providesHeroesService(
        repository: ICharactersRepository
    ) = HeroesService(repository)
}