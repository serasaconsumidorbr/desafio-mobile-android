package com.ncz.desafio_mobile_android.infrastructure.datasources

import com.ncz.desafio_mobile_android.infrastructure.dto.CharacterDto

interface InterfaceCharacterDataSource{

    suspend fun getCharacter(limit: Int, ts:String , publicKey: String,hash: String ): List<CharacterDto>
}