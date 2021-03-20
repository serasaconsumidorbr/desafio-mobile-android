package com.drawiin.marvelcharacters.data.repository

import com.drawiin.marvelcharacters.data.network.model.CharacterDtoMapper
import com.drawiin.marvelcharacters.data.network.service.MavelService
import com.drawiin.marvelcharacters.domain.boundarys.CharactersRepository
import com.drawiin.marvelcharacters.domain.model.character.Character
import com.drawiin.marvelcharacters.utils.getMd5Digest

class DefaultCharactersRepository(
    private val mavelService: MavelService,
    private val characterDtoMapper: CharacterDtoMapper

) : CharactersRepository {
    override suspend fun getHeroes(
        apiKey: String,
        hash: String,
        limit: Int,
        offset: Int
    ): List<Character> {
        val timestamp = System.currentTimeMillis().toInt()
        val md5Hash = "$timestamp$hash$apiKey".getMd5Digest()
        val result = mavelService.getCharacters(
            apiKey,
            md5Hash,
            timestamp,
            limit,
            offset
        ).data?.results ?: emptyList()

        return characterDtoMapper.mapToDomainList(result)
    }
}