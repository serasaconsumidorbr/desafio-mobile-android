package com.drawiin.myheroes.repository

import com.drawiin.myheroes.network.HeroesService

class DefaultHeroesRepository(
    private val heroesService: HeroesService
) : HeroesRepository