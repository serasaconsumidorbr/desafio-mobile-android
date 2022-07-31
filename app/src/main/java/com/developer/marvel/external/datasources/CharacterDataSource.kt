package com.developer.marvel.external.datasources

import com.developer.marvel.external.service.CharacterService
import com.developer.marvel.infrastructure.datasources.CharacterDataSource
import com.developer.marvel.infrastructure.dto.CharacterDto
import retrofit2.Retrofit

open class CharacterDataSourceImpl(private val retrofit: Retrofit) : CharacterDataSource {

    private val characterService by lazy { retrofit.create(CharacterService::class.java) }

    override suspend fun getCharacters(
        limit: Int,
    ): List<CharacterDto> {
        val response = characterService.getCharacters(limit)
        return response.data.results ?: emptyList()
    }
}