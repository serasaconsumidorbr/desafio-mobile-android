package welias.marvel.core.extensions

import welias.marvel.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

fun generateMD5Hash(): String {
    val keys = "${getTimeStamp()}${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(keys.toByteArray()))
        .toString(16)
        .padStart(32, '0')
}

fun getTimeStamp(): String {
    return (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
}
