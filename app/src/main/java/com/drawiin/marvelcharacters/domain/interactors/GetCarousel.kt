package com.drawiin.marvelcharacters.domain.interactors

import com.drawiin.marvelcharacters.domain.boundarys.CharactersRepository
import com.drawiin.marvelcharacters.domain.model.character.Character

class GetCarousel(
    private val charactersRepository: CharactersRepository
) {
    suspend fun execute(
        apikey: String,
        hash: String
    ): List<Character> {
        return charactersRepository.getHeroes(apikey, hash, 5, 0)
    }
}