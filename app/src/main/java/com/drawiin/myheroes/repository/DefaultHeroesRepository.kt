package com.drawiin.myheroes.repository

import com.drawiin.myheroes.network.service.HeroesService
import com.drawiin.myheroes.utils.toMd5

class DefaultHeroesRepository(
    private val heroesService: HeroesService
) : HeroesRepository {
    override suspend fun getHeroes(apiKey: String, hash: String) {
        val timestamp = System.currentTimeMillis().toInt()
        val md5Hash = "$timestamp$hash$apiKey".toMd5()
        heroesService.getCharacters(apiKey, md5Hash, timestamp)
    }
}