package com.marvelverse.data.datasources

import androidx.lifecycle.map
import com.marvelverse.data.db.MarvelCharacterEntity
import com.marvelverse.data.db.MarvelCharacterFavoritesDao
import com.marvelverse.data.db.asDomainModel
import com.marvelverse.domain.MarvelCharacter
import javax.inject.Inject

class FavoritesDbDataSource @Inject constructor(private val marvelCharacterFavoritesDao: MarvelCharacterFavoritesDao) {

    val favoriteCharacters = marvelCharacterFavoritesDao.getAll().map { it.asDomainModel() }

    fun exists(marvelCharacter: MarvelCharacter): Boolean {
        val character = marvelCharacterFavoritesDao.findByName(marvelCharacter.characterName)
        return character != null
    }

    fun addToFavorites(marvelCharacter: MarvelCharacter) {
        val marvelCharacterEntity = MarvelCharacterEntity(marvelCharacter.characterName,
            marvelCharacter.description,
            marvelCharacter.thumbnailImage.url,
            marvelCharacter.thumbnailImage.extension)
        marvelCharacterFavoritesDao.insert(marvelCharacterEntity)
    }

    fun removeFromFavorites(marvelCharacter: MarvelCharacter) {
        marvelCharacterFavoritesDao.deleteBy(marvelCharacter.characterName)
    }
}