package com.victorvgc.mymarvelheros.home.data.data_source

import com.victorvgc.mymarvelheros.home.data.database.HeroDao
import com.victorvgc.mymarvelheros.home.data.model.local.LocalHero
import com.victorvgc.mymarvelheros.home.domain.data_source.HeroesDataSource
import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.domain.domain.HeroesPage

class LocalHeroesDataSourceImpl(private val heroDao: HeroDao) : HeroesDataSource.Local {
    override suspend fun saveInitialHeroes(heroList: List<Hero>) {
        heroList.forEach { hero ->
            heroDao.insertHero(LocalHero.fromModel(hero))
        }
    }

    override suspend fun saveHeroesPage(heroesPage: HeroesPage) {
        heroesPage.heroesList.forEach { hero ->
            heroDao.insertHero(
                LocalHero.fromModel(
                    hero = hero,
                    offset = heroesPage.pageOffset
                )
            )
        }
    }

    override suspend fun getInitialHeroes(): List<Hero> {
        val databaseResponse = heroDao.getInitialHeroes()

        return databaseResponse.map { it.toModel() }
    }

    override suspend fun getHeroesPage(offset: Int): HeroesPage {
        val databaseResponse = heroDao.getHeroPage(offset)

        return HeroesPage(
            pageOffset = offset,
            pageSize = databaseResponse.size,
            heroesList = databaseResponse.map { it.toModel() }
        )
    }
}