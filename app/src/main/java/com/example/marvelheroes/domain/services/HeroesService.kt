package com.example.marvelheroes.domain.services

import com.example.marvelheroes.BuildConfig
import com.example.marvelheroes.data.retrofit.RetrofitServiceGeneric
import com.example.marvelheroes.domain.repositories.ICharactersRepository
import com.example.marvelheroes.domain.models.characters.Character
import dagger.hilt.android.scopes.ActivityScoped
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Inject

const val HERO_LIMIT = 10

abstract class Heroes : RetrofitServiceGeneric<Unit, List<Character>>()

@ActivityScoped
class HeroesService @Inject constructor(
    private val repository: ICharactersRepository
) : Heroes() {
    override suspend fun execute(param: Unit): List<Character> {
        val limit = HERO_LIMIT
        val timestamp = timesTamp()
        val publicKey = BuildConfig.PUBLIC_KEY_MARVEL
        val hash = hashMd5(timestamp)
        val response =  repository.getCharactersAsync(limit, timestamp, publicKey, hash)
        return response.data.results?.map { it.mapToEntity() } ?: emptyList()
    }

    private fun hashMd5(timestamp: String): String {
        val hash = "${timestamp}${BuildConfig.PRIVATE_KEY_MARVEL}${BuildConfig.PUBLIC_KEY_MARVEL}"
        var m: MessageDigest? = null
        try {
            m = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        m?.update(hash.toByteArray(), 0, hash.length)
        return BigInteger(1, m?.digest()).toString(16)
    }

    private fun timesTamp() = System.currentTimeMillis().toString()
}