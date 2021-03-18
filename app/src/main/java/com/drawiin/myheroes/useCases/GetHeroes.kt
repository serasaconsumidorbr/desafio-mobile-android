package com.drawiin.myheroes.useCases

import com.drawiin.myheroes.domain.model.character.Character
import com.drawiin.myheroes.repository.HeroesRepository

class GetHeroes (
    private val heroesRepository: HeroesRepository
) {
    suspend fun execute(apikey: String, hash: String): List<Character> {
        return heroesRepository.getHeroes(apikey, hash)
    }
}