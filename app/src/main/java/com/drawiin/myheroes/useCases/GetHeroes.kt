package com.drawiin.myheroes.useCases

import com.drawiin.myheroes.repository.HeroesRepository
import javax.inject.Inject

class GetHeroes (
    private val heroesRepository: HeroesRepository
) {
    suspend fun execute(apikey: String, hash: String) {
        heroesRepository.getHeroes(apikey, hash)
    }
}