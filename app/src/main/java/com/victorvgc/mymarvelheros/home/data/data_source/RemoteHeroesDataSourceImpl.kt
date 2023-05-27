package com.victorvgc.mymarvelheros.home.data.data_source

import com.victorvgc.mymarvelheros.BuildConfig
import com.victorvgc.mymarvelheros.home.data.service.HeroesService
import com.victorvgc.mymarvelheros.home.data.utils.DateUtils
import com.victorvgc.mymarvelheros.home.data.utils.HashUtils
import com.victorvgc.mymarvelheros.home.domain.data_source.HeroesDataSource
import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.domain.domain.HeroesPage

class RemoteHeroesDataSourceImpl(
    private val heroesService: HeroesService,
    private val hashUtils: HashUtils,
    private val dateUtils: DateUtils
) :
    HeroesDataSource.Remote {
    override suspend fun getInitialHeroes(): List<Hero> {
        val timestamp = dateUtils.getCurrentTimestamp()
        val key = BuildConfig.API_KEY
        val pKey = BuildConfig.PRIVATE_KEY
        val hash = hashUtils.getMD5From(timestamp, pKey, key)

        val response = heroesService.getCharacters(
            apiKey = key,
            timestamp = timestamp,
            hash = hash,
            offset = 0,
            limit = HeroesPage.DEFAULT_INIT_PAGE_SIZE
        )

        if (response.isSuccessful) {
            val body = response.body()

            if (body != null) {
                return body.data.results.map { it.toModel() }
            }
        }

        return emptyList()
    }

    override suspend fun getHeroesPage(offset: Int): HeroesPage {
        val timestamp = dateUtils.getCurrentTimestamp()
        val key = BuildConfig.API_KEY
        val pKey = BuildConfig.PRIVATE_KEY
        val hash = hashUtils.getMD5From(timestamp, pKey, key)

        val response = heroesService.getCharacters(
            apiKey = key,
            timestamp = timestamp,
            hash = hash,
            offset = offset,
            limit = HeroesPage.DEFAULT_PAGE_SIZE
        )

        if (response.isSuccessful) {
            val body = response.body()

            if (body != null) {
                return body.data.toModel()
            }
        }

        return HeroesPage(
            pageSize = 0,
            pageOffset = offset,
            heroesList = emptyList()
        )
    }
}