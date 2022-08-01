package com.developer.marvel.utils

import android.annotation.SuppressLint
import java.lang.RuntimeException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.md5(): String {
    if (this.isBlank()) return ""

    try {
        val m: MessageDigest = MessageDigest.getInstance("MD5")
        m.update(this.toByteArray(), 0, this.length)
        return BigInteger(1, m.digest()).toString(16)

    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}

@SuppressLint("SimpleDateFormat")
fun String.toDatetime(): Date {
    if (this.isBlank()) return Date()

    return try {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        simpleDateFormat.parse(this) as Date
    } catch (e: ParseException) {
        throw RuntimeException(e)
    }

}