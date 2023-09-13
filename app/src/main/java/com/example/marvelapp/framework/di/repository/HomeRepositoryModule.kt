package com.example.marvelapp.framework.di.repository

import com.example.marvelapp.features.characterslist.data.repository.HomeRepository
import com.example.marvelapp.features.characterslist.data.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeRepositoryModule {

    @Binds
    abstract fun homeRepositoryModule(repository: HomeRepositoryImpl): HomeRepository
}