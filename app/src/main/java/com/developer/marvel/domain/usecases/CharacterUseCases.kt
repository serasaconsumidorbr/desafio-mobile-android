package com.developer.marvel.domain.usecases

import com.developer.marvel.domain.repositories.CharacterRepository

class CharacterUseCases(val repository: CharacterRepository) {

    suspend fun getCharacters(page: Int = 1, limit: Int): List<Character> {
        return listOf()
    }

}