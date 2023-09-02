package com.example.marvel_app.framework.di

import com.example.core.features.characters.data.datasource.CharactersRemoteDatasource
import com.example.core.features.characters.data.repository.CharactersRepository
import com.example.marvel_app.features.characters.remote.datasource.CharactersRemoteDatasourceImpl
import com.example.marvel_app.features.characters.remote.repository.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCharacterRepository(
        repositoryImpl: CharactersRepositoryImpl
    ): CharactersRepository

    @Binds
    fun bindCharactersRemoteDatasource(
        datasource: CharactersRemoteDatasourceImpl
    ): CharactersRemoteDatasource
}