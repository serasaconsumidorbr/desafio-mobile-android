package com.drawiin.myheroes.repository

import com.drawiin.myheroes.domain.model.character.Character
import com.drawiin.myheroes.network.model.CharacterDtoMapper
import com.drawiin.myheroes.network.service.HeroesService
import com.drawiin.myheroes.utils.getMd5Digest

class DefaultHeroesRepository(
    private val heroesService: HeroesService,
    private val characterDtoMapper: CharacterDtoMapper

) : HeroesRepository {
    override suspend fun getHeroes(apiKey: String, hash: String): List<Character> {
        val timestamp = System.currentTimeMillis().toInt()
        val md5Hash = "$timestamp$hash$apiKey".getMd5Digest()
        val result = heroesService.getCharacters(apiKey, md5Hash, timestamp).data?.results
            ?: emptyList()
        return characterDtoMapper.mapToDomainList(result)
    }
}