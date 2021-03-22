package com.drawiin.marvelcharacters.domain.boundarys

import com.drawiin.marvelcharacters.domain.model.character.Character

interface CharactersRepository {
    suspend fun getCharacters(
        apiKey: String,
        hash: String,
        limit: Int,
        offset: Int
    ): List<Character>
}