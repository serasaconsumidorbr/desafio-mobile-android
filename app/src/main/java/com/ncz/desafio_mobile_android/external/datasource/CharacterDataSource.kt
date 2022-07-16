package com.ncz.desafio_mobile_android.external.datasource

import com.ncz.desafio_mobile_android.external.api.RetrofitInstance
import com.ncz.desafio_mobile_android.external.service.CharacterService
import com.ncz.desafio_mobile_android.infrastructure.datasources.InterfaceCharacterDataSource
import com.ncz.desafio_mobile_android.infrastructure.dto.CharacterDto

open class CharacterDataSource : InterfaceCharacterDataSource {

    private val characterService by lazy { RetrofitInstance.retrofit.create(CharacterService::class.java) }
    override suspend fun getCharacter(
        limit: Int,
        ts: String,
        publicKey: String,
        hash: String
    ): List<CharacterDto> {
        return characterService.getCharacter(limit, ts, publicKey, hash)
            .map { character -> character.data.results?.first()!! }
    }
}