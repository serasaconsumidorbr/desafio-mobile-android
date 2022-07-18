package com.ncz.desafio_mobile_android.domain.usescases

import com.ncz.desafio_mobile_android.domain.repositories.InterfaceCharacterRepository

class CharacterUseCases(private val repository: InterfaceCharacterRepository) {

    suspend fun getCharacter() = repository.getCharacter()
}

