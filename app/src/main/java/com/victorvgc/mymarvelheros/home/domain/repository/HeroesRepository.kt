package com.victorvgc.mymarvelheros.home.domain.repository

import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.domain.domain.HeroPage

interface HeroesRepository {
    suspend fun getInitialHeroes(): List<Hero>

    suspend fun getHeroesPage(offset: Int, limit: Int): HeroPage
}