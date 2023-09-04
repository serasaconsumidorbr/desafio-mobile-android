package com.example.marvel_app.features.characters.local.repository

import com.example.core.features.characters.domain.model.Character
import com.example.core.features.favorites.data.datasource.FavoritesLocalDatasource
import com.example.core.features.favorites.data.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoritesLocalDatasource: FavoritesLocalDatasource
): FavoritesRepository {

    override fun getAll(): Flow<List<Character>> {
        return favoritesLocalDatasource.getAll()
    }

    override suspend fun saveFavorite(character: Character) {
        return favoritesLocalDatasource.save(character)
    }

    override suspend fun deleteFavorite(character: Character) {
        return favoritesLocalDatasource.delete(character)
    }
}