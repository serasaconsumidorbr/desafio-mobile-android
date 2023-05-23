package com.example.testeapp.common

object ApiUtils {
    val timestamp = System.currentTimeMillis().toString()
    val hash = generateHash(timestamp, "b19ac6c7653109903d03f1677f642616d479f849", "86fbd060c28b8796cd096fc98f7214c2")


    fun generateHash(timestamp: String, privateKey: String, publicKey: String): String {
        val input = "$timestamp$privateKey$publicKey"
        val md = java.security.MessageDigest.getInstance("MD5")
        val digest = md.digest(input.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }
}