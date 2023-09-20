package com.marvelverse.data.network

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.time.Instant

data class MarvelAPI(
    val baseUrl: String = "https://gateway.marvel.com:443/v1/public/",
    val apiKey: String,
    private val privateKey: String,
) {
    val timestamp: Long = Instant.now().toEpochMilli()
    val hash: String = String(Hex.encodeHex(DigestUtils.md5("$timestamp$privateKey$apiKey")))
}