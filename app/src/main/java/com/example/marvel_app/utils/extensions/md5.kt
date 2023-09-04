package com.example.marvel_app.utils.extensions

import java.math.BigInteger
import java.security.MessageDigest

//Função de extensão para criptografia md5
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}