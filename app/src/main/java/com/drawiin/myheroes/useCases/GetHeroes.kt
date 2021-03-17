package com.drawiin.myheroes.useCases

import com.drawiin.myheroes.repository.HeroesRepository
import javax.inject.Inject

class GetHeroes @Inject constructor(
    private val heroesRepository: HeroesRepository
) {
    private fun execute() {}
}