package com.drawiin.marvelcharacters.domain.interactors

import com.drawiin.marvelcharacters.domain.boundarys.CharactersRepository
import com.drawiin.marvelcharacters.domain.model.character.Character

class GetCharacters (
    private val charactersRepository: CharactersRepository
) {
    suspend fun execute(apiKey: String, hash: String, page: Int): List<Character> {
        val offset = (NETWORK_PAGE_LIMIT * page) + DEFAULT_OFFSET
        return charactersRepository.getCharacters(apiKey, hash, NETWORK_PAGE_LIMIT, offset)
    }

    companion object {
        const val DEFAULT_OFFSET = 5
        const val NETWORK_PAGE_LIMIT = 20
    }
}