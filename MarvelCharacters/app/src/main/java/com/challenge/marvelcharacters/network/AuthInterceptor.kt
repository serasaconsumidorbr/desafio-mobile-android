package com.challenge.marvelcharacters.network

import com.challenge.marvelcharacters.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val originalURL = original.url
        val ts =  System.currentTimeMillis().toString()
        val url =  originalURL.newBuilder()
            .addQueryParameter(Constants.TS, ts)
            .addQueryParameter(Constants.API_KEY, Constants.PUBLIC_KEY)
            .addQueryParameter(Constants.HASH, getHash(ts))
            .build()
        return chain.proceed(original.newBuilder().url(url).build())
    }

    private fun getHash(ts : String) : String{
        val md = MessageDigest.getInstance("MD5")
        val str = ts + Constants.PRIVATE_KEY + Constants.PUBLIC_KEY
        return BigInteger(1,md.digest(str.toByteArray())).toString(16).padStart(32,'0')
    }
}