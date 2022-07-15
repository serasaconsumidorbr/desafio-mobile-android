package com.ncz.desafio_mobile_android.infrastructure.datasources

import com.ncz.desafio_mobile_android.domain.entities.character.Character

interface InterfaceCharacterDataSource{
    suspend fun getCharacter(): List<Character>
}