package com.developer.marvel.domain.repositories

import com.developer.marvel.domain.entities.Character

interface CharacterRepository {
    suspend fun getCharacters(page: Int, limit: Int): List<Character>
}