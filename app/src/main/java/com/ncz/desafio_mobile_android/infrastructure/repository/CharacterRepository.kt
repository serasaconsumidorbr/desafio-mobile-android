package com.ncz.desafio_mobile_android.infrastructure.repository

import com.ncz.desafio_mobile_android.domain.entities.character.Character
import com.ncz.desafio_mobile_android.domain.repositories.InterfaceCharacterRepository
import com.ncz.desafio_mobile_android.infrastructure.datasources.InterfaceCharacterDataSource
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class CharacterRepository(
    private val dataSource: InterfaceCharacterDataSource
) : InterfaceCharacterRepository {

    private val publicKey = "468f291338a63b4e512fa3d0be729577"
    private val privateKey = "704e950dfa830be865737e26ac480214cfccdce9"
    private val timestamp = System.currentTimeMillis().toString()


    private fun md5(): String {
        val hash = "$timestamp$privateKey$publicKey"
        var m: MessageDigest? = null
        try {
            m = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        m?.update(hash.toByteArray(), 0, hash.length)
        return BigInteger(1, m?.digest()).toString(16)
    }

    override suspend fun getCharacter(): List<Character> {
        val characters = dataSource.getCharacter(100, timestamp, publicKey,md5() )

        return characters.map { charactersDto -> charactersDto.mapToEntity() }
    }

}