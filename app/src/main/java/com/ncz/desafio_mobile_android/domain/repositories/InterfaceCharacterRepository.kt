package com.ncz.desafio_mobile_android.domain.repositories

import com.ncz.desafio_mobile_android.domain.entities.character.Character

interface InterfaceCharacterRepository {
    suspend fun getCharacter(): List<Character>
}