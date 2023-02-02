package com.example.marvelheroes.domain.services

import com.example.marvelheroes.BuildConfig
import com.example.marvelheroes.data.dto.ApiResponse
import com.example.marvelheroes.data.retrofit.RetrofitServiceGeneric
import com.example.marvelheroes.domain.repositories.ICharactersRepository
import dagger.hilt.android.scopes.ActivityScoped
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Inject

const val HERO_LIMIT = 50

abstract class Heroes : RetrofitServiceGeneric<Unit, ApiResponse>()

@ActivityScoped
class HeroesService @Inject constructor(
    private val repository: ICharactersRepository
) : Heroes() {
    override suspend fun execute(param: Unit): ApiResponse {
        val limit = HERO_LIMIT
        val timestamp = timesTamp()
        val publicKey = BuildConfig.PUBLIC_KEY_MARVEL
        val hash = hashMd5(timestamp)
        return repository.getCharactersAsync(limit, timestamp, publicKey, hash)
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