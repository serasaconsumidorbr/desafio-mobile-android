package com.victorvgc.mymarvelheros.home.data.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class HashUtils {
    fun getMD5From(vararg params: String): String {
        var fullString = ""

        for (string in params) {
            fullString += string
        }

        try {
            val md5 = MessageDigest.getInstance("MD5")

            md5.update(fullString.toByteArray())

            val digest = md5.digest()

            val stringBuilder = StringBuilder()

            digest.forEach { bytes ->
                var h = Integer.toHexString(0xFF.and(bytes.toInt()))

                while (h.length < 2) {
                    h = "0$h"
                }

                stringBuilder.append(h)
            }

            return stringBuilder.toString()
        } catch (e: NoSuchAlgorithmException) {
            return ""
        }

    }
}