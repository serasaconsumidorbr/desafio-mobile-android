package com.ncz.desafio_mobile_android.external.datasource

import com.ncz.desafio_mobile_android.domain.entities.character.Character
import com.ncz.desafio_mobile_android.infrastructure.datasources.InterfaceCharacterDataSource

open class CharacterDataSource: InterfaceCharacterDataSource {
    override suspend fun getCharacter(): List<Character> {
        TODO("Not yet implemented")
    }
}