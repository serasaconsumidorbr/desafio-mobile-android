package com.victorvgc.mymarvelheros.home.domain.use_case

import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.domain.repository.HeroesRepository

class GetInitialHeroes(private val heroesRepository: HeroesRepository) {
    suspend operator fun invoke(): List<Hero> {
        return heroesRepository.getInitialHeroes()
    }
}