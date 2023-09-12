package com.example.marvelapp.framework.service.interceptors

import com.example.marvelapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MarvelApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val timestamp = System.currentTimeMillis().toString()
        val hash =
            generateMarvelAPIDigest(timestamp)

        val modifiedRequest = request.newBuilder()
            .url(
                request.url.newBuilder()
                    .addEncodedQueryParameter("ts", timestamp)
                    .addEncodedQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
                    .addEncodedQueryParameter("hash", hash)
                    .build()
            ).build()

        return chain.proceed(modifiedRequest)
    }

    private fun generateMarvelAPIDigest(
        timestamp: String
    ): String {
        val publicKey = BuildConfig.PUBLIC_KEY
        val privateKey = BuildConfig.PRIVATE_KEY
        val input = "$timestamp$privateKey$publicKey"
        try {
            val md5 = MessageDigest.getInstance("MD5")
            val digest = md5.digest(input.toByteArray())
            val hexString = StringBuilder()
            for (byte in digest) {
                hexString.append(String.format("%02x", byte))
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            throw RuntimeException("MD5 algorithm not found")
        }
    }
}