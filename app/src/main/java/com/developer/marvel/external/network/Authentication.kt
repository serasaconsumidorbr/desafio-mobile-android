package com.developer.marvel.external.network

import com.developer.marvel.BuildConfig
import com.developer.marvel.utils.md5

object Authentication {

    data class AccessToken(
        val timestamp: Long,
        val publicKey: String,
        val token: String
    )

    fun generateAccessToken(): AccessToken {
        val publicKey = BuildConfig.API_PUBLIC_KEY
        val timestamp = System.currentTimeMillis()
        val token = getHash(publicKey, timestamp)

        return AccessToken(
            timestamp,
            publicKey,
            token
        )
    }

    private fun getHash(publicKey: String, timestamp: Long): String {
        val privateKey = BuildConfig.API_PRIVATE_KEY
        return "$timestamp$privateKey$publicKey".md5()
    }
}