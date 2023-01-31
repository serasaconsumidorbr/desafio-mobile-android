package welias.marvel.core.extensions

import welias.marvel.BuildConfig
import welias.marvel.core.constants.*
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

fun generateMD5Hash(): String {
    val keys = "${getTimeStamp()}${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"
    val md = MessageDigest.getInstance(MD5)
    return BigInteger(ONE, md.digest(keys.toByteArray()))
        .toString(SIXTEEN)
        .padStart(THIRD_TWO, ZERO.toChar())
}

fun getTimeStamp(): String {
    return (Calendar.getInstance(TimeZone.getTimeZone(UTC)).timeInMillis / HUNDRED).toString()
}
