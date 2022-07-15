package com.ncz.desafio_mobile_android.external.config

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

open class Settings {
    val baseUrl = "http://gateway.marvel.com/"
    val publicKey = "468f291338a63b4e512fa3d0be729577"
    val privateKey = "704e950dfa830be865737e26ac480214cfccdce9"
    val timestamp = System.currentTimeMillis().toString()


    fun md5(): String {
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
}



