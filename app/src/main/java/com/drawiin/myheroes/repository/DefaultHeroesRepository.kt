package com.drawiin.myheroes.repository

import com.drawiin.myheroes.network.service.HeroesService

class DefaultHeroesRepository(
    private val heroesService: HeroesService
) : HeroesRepository {
    override suspend fun getHeroes(apiKey: String) {}
}