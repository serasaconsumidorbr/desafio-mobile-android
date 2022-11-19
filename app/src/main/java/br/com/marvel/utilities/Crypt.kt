package br.com.marvel.utilities

import br.com.marvel.utilities.Constants.Companion.PRIVATE_KEY
import java.math.BigInteger
import java.security.MessageDigest

class Crypt {

    fun md5Hash(ts: String, publicKey: String): String {
        val privateKey = PRIVATE_KEY

        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest("$ts$privateKey$publicKey".toByteArray())).toString(16)
            .padStart(32, '0')
    }

}