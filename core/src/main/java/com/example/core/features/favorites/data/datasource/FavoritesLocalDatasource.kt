package com.example.core.features.favorites.data.datasource

import com.example.core.features.characters.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoritesLocalDatasource {

    fun getAll(): Flow<List<Character>>

    suspend fun save(character: Character)

    suspend fun delete(character: Character)
}