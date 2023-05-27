package com.victorvgc.mymarvelheros.home.domain.repository

import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.domain.domain.HeroesPage

interface HeroesRepository {
    suspend fun getInitialHeroes(): List<Hero>

    suspend fun getHeroesPage(offset: Int): HeroesPage
}