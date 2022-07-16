package com.ncz.desafio_mobile_android.infrastructure.repository

import com.ncz.desafio_mobile_android.domain.entities.character.Character
import com.ncz.desafio_mobile_android.domain.repositories.InterfaceCharacterRepository
import com.ncz.desafio_mobile_android.external.config.Settings
import com.ncz.desafio_mobile_android.infrastructure.datasources.InterfaceCharacterDataSource

class CharacterRepository(
    private val dataSource: InterfaceCharacterDataSource
) : InterfaceCharacterRepository {
    private val settings = Settings()

    override suspend fun getCharacter(): List<Character> {
        val ts = settings.timestamp
        val publicKey = settings.publicKey
        val hash = "$ts${settings.privateKey}$publicKey"
        val characters = dataSource.getCharacter(100, ts, publicKey, hash)

        return characters.map { charactersDto -> charactersDto.mapToEntity() }
    }

}