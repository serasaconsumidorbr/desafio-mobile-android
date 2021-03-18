package com.drawiin.myheroes.utils

import java.math.BigInteger
import java.security.MessageDigest

fun String.getMd5Digest(): String {
    val md = MessageDigest.getInstance("MD5")
    val byteArray = this.toByteArray()
    val bigInt = BigInteger(1, md.digest(byteArray))
    return bigInt.toString(16).padStart(32, '0')
}