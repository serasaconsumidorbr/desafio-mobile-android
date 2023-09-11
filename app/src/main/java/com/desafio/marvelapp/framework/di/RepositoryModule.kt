package com.desafio.marvelapp.framework.di

import com.desafio.marvelapp.framework.CharactersRepositoryImpl
import com.desafio.marvelapp.framework.network.response.DataWrapperResponse
import com.desafio.marvelapp.framework.remote.RetrofitCharactersDataSource
import com.project.core.data.repository.ICharactersRemoteDataSource
import com.project.core.data.repository.ICharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCharactersRepository(
        charactersRepositoryImpl: CharactersRepositoryImpl
    ): ICharactersRepository

    @Binds
    fun bindRemoteDataSource(
        dataSource: RetrofitCharactersDataSource
    ): ICharactersRemoteDataSource<DataWrapperResponse>
}