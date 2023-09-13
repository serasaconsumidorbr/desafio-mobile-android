package com.example.marvelapp.framework.di.repository

import com.example.marvelapp.features.comics.data.repository.ComicsRepository
import com.example.marvelapp.features.comics.data.repository.ComicsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ComicsRepositoryModule {

    @Binds
    abstract fun comicsRepositoryModule(repository: ComicsRepositoryImpl): ComicsRepository
}