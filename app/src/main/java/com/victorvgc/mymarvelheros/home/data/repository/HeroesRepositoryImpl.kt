package com.victorvgc.mymarvelheros.home.data.repository

import com.victorvgc.mymarvelheros.home.domain.data_source.HeroesDataSource
import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.domain.domain.HeroesPage
import com.victorvgc.mymarvelheros.home.domain.repository.HeroesRepository

class HeroesRepositoryImpl(
    private val localDataSource: HeroesDataSource.Local,
    private val remoteDataSource: HeroesDataSource.Remote
) : HeroesRepository {
    override suspend fun getInitialHeroes(): List<Hero> {
        val remote = remoteDataSource.getInitialHeroes()

        if (remote.isNotEmpty()) {
            localDataSource.saveInitialHeroes(remote)
        }

        return localDataSource.getInitialHeroes()
    }

    override suspend fun getHeroesPage(offset: Int): HeroesPage {
        val remote = remoteDataSource.getHeroesPage(offset)

        if (remote.heroesList.isNotEmpty()) {
            localDataSource.saveHeroesPage(remote)
        }

        return localDataSource.getHeroesPage(offset)
    }
}