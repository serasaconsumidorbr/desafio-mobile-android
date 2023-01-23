package com.example.data.heroes.di

import com.example.data.heroes.HeroRepositoryImpl
import com.example.domain.heroes.repository.HeroRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideHeroRepository(repository: HeroRepositoryImpl): HeroRepository

}