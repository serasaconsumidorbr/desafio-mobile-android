package com.example.marvelapp.framework.di.repository

import com.example.marvelapp.features.characterdetail.data.repository.CharacterDetailRepository
import com.example.marvelapp.features.characterdetail.data.repository.CharacterDetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CharacterDetailRepositoryModule {

    @Binds
    abstract fun characterDetailRepositoryModule(repository: CharacterDetailRepositoryImpl): CharacterDetailRepository
}