package com.victorvgc.mymarvelheros.home.domain.data_source

import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.domain.domain.HeroPage

interface HeroPageDataSource {
    interface Local {
        suspend fun saveInitialHeroes(heroList: List<Hero>)

        suspend fun saveHeroesPage(heroPage: HeroPage)

        suspend fun getInitialHeroes(): List<Hero>

        suspend fun getHeroesPage(offset: Int, limit: Int): HeroPage
    }

    interface Remote {
        suspend fun getInitialHeroes(): List<Hero>

        suspend fun getHeroesPage(offset: Int, limit: Int): HeroPage
    }
}