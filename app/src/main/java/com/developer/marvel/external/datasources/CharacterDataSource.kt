package com.developer.marvel.external.datasources

import com.developer.marvel.external.datasources.helper.ResponseError
import com.developer.marvel.external.service.CharacterService
import com.developer.marvel.infrastructure.datasources.CharacterDataSource
import com.developer.marvel.infrastructure.dto.CharacterDto
import com.developer.marvel.infrastructure.dto.service.ResponseDto
import retrofit2.Retrofit

open class CharacterDataSourceImpl(private val retrofit: Retrofit) : CharacterDataSource {

    private val characterService by lazy { retrofit.create(CharacterService::class.java) }

    override suspend fun getCharacters(
        page: Int, limit: Int
    ): List<CharacterDto> {
        val response = characterService.getCharacters(offset = limit * (page - 1), limit = limit)

        if (!response.isSuccessful) {
            throw ResponseError(response.code(), response.errorBody()).getException()
        }

        val body = response.body() as ResponseDto<CharacterDto>
        return body.data.results ?: emptyList()
    }
}