package com.developer.marvel.domain.usecases

import com.developer.marvel.domain.entities.Character
import com.developer.marvel.domain.repositories.CharacterRepository

class CharacterUseCases(val repository: CharacterRepository) {

    suspend fun getCharacters(page: Int, limit: Int): List<Character> {
        return repository.getCharacters(page, limit)
    }

}