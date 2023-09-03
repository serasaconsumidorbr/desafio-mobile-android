package com.example.marvel_app.features.favorites.local.datasource

import com.example.core.features.characters.domain.model.Character
import com.example.core.features.favorites.data.datasource.FavoritesLocalDatasource
import com.example.marvel_app.framework.db.dao.FavoriteDao
import com.example.marvel_app.framework.db.entity.FavoriteEntity
import com.example.marvel_app.framework.db.entity.toCharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteLocalDatasourceImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
): FavoritesLocalDatasource {

    override fun getAll(): Flow<List<Character>> {
        return favoriteDao.loadFavorites().map {
            it.toCharacterModel()
        }
    }

    override suspend fun save(character: Character) {
        return favoriteDao.insertFavorite(character.toFavoriteEntity())
    }

    override suspend fun delete(character: Character) {
        return favoriteDao.deleteFavorite(character.toFavoriteEntity())
    }

    private fun Character.toFavoriteEntity() = FavoriteEntity(id, name, imageUrl)
}