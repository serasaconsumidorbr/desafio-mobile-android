package com.ncz.desafio_mobile_android.external.datasource

import com.ncz.desafio_mobile_android.external.service.CharacterService
import com.ncz.desafio_mobile_android.infrastructure.datasources.InterfaceCharacterDataSource
import com.ncz.desafio_mobile_android.infrastructure.dto.CharacterDto
import retrofit2.Retrofit

open class CharacterDataSource(private val retrofit: Retrofit) : InterfaceCharacterDataSource {

    private val characterService by lazy { retrofit.create(CharacterService::class.java) }
    override suspend fun getCharacter(
        limit: Int,
        ts: String,
        publicKey: String,
        hash: String
    ): List<CharacterDto> {
        val response = characterService.getCharacter(limit, ts, publicKey, hash)
        return response.data.results!!
    }
}