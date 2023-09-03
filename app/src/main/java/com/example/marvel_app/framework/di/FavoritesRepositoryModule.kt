package com.example.marvel_app.framework.di

import com.example.core.features.favorites.data.datasource.FavoritesLocalDatasource
import com.example.core.features.favorites.data.repository.FavoritesRepository
import com.example.marvel_app.features.favorites.local.datasource.FavoriteLocalDatasourceImpl
import com.example.marvel_app.features.favorites.local.repository.FavoriteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesRepositoryModule {

    @Binds
    fun bindFavoritesRepository(
        repository: FavoriteRepositoryImpl
    ): FavoritesRepository

    @Binds
    fun bindFavoriteLocalDatasource(
        datasource: FavoriteLocalDatasourceImpl
    ): FavoritesLocalDatasource
}