package com.victorvgc.mymarvelheros.home.domain.use_case

import com.victorvgc.mymarvelheros.home.domain.domain.HeroesPage
import com.victorvgc.mymarvelheros.home.domain.repository.HeroesRepository

class GetHeroesPage(private val heroesRepository: HeroesRepository) {
    suspend operator fun invoke(offset: Int): HeroesPage {
        return heroesRepository.getHeroesPage(offset)
    }
}