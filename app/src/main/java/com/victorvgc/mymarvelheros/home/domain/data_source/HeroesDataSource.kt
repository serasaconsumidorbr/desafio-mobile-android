package com.victorvgc.mymarvelheros.home.domain.data_source

import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.domain.domain.HeroesPage

interface HeroesDataSource {
    interface Local {
        suspend fun saveInitialHeroes(heroList: List<Hero>)

        suspend fun saveHeroesPage(heroesPage: HeroesPage)

        suspend fun getInitialHeroes(): List<Hero>

        suspend fun getHeroesPage(offset: Int): HeroesPage
    }

    interface Remote {
        suspend fun getInitialHeroes(): List<Hero>

        suspend fun getHeroesPage(offset: Int): HeroesPage
    }
}