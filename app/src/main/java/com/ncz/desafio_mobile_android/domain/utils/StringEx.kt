package com.ncz.desafio_mobile_android.domain.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Date


fun String.md5(timestamp: Date, privateKey: String, publicKey: String): String {
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