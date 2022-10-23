package com.example.util.hash.impl

import com.example.util.hash.Md5HashGenerator
import java.math.BigInteger
import java.security.MessageDigest

class Md5HashGeneratorImpl: Md5HashGenerator {
    override fun invoke(timestamp: Long, publicKey: String, privateKey: String): String {
        val input = "$timestamp$privateKey$publicKey"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(
            1, md.digest(input.toByteArray())
        ).toString(16).padStart(32, '0')
    }
}