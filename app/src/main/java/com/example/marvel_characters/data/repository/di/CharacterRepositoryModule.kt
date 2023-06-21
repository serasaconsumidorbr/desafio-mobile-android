package com.example.marvel_characters.data.repository.di

import com.example.marvel_characters.data.repository.CharacterRepository
import com.example.marvel_characters.data.repository.MarvelCharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharacterRepositoryModule {

    @Binds
    fun bindRepository(repository: MarvelCharacterRepository): CharacterRepository
}