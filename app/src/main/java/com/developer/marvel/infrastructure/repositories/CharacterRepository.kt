package com.developer.marvel.infrastructure.repositories

import com.developer.marvel.domain.entities.Character
import com.developer.marvel.domain.repositories.CharacterRepository
import com.developer.marvel.infrastructure.datasources.CharacterDataSource

class CharacterRepositoryImpl(
    private val dataSource: CharacterDataSource
) : CharacterRepository {

    override suspend fun getCharacters(page: Int, limit: Int): List<Character> {
        val characters = dataSource.getCharacters(page, limit)
        return characters.map { it.mapperToEntity() }
    }

}