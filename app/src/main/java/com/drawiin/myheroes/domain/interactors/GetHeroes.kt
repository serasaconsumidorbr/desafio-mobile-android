package com.drawiin.myheroes.domain.interactors

import com.drawiin.myheroes.domain.model.character.Character
import com.drawiin.myheroes.domain.boundarys.HeroesRepository

class GetHeroes (
    private val heroesRepository: HeroesRepository
) {
    suspend fun execute(apikey: String, hash: String): List<Character> {
        return heroesRepository.getHeroes(apikey, hash)
    }
}