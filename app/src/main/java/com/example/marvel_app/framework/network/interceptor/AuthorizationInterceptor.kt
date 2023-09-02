package com.example.marvel_app.framework.network.interceptor

import com.example.marvel_app.utils.Constants.QUERY_PARAMETER_API_KEY
import com.example.marvel_app.utils.Constants.QUERY_PARAMETER_HASH
import com.example.marvel_app.utils.Constants.QUERY_PARAMETER_TS
import com.example.marvel_app.utils.extensions.md5
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Calendar

class AuthorizationInterceptor(
    private val publicKey: String,
    private val privateKey: String,
    private val calendar: Calendar
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url

        val ts = (calendar.timeInMillis / 1000L).toString()

        val hash = "$ts$privateKey$publicKey".md5()

        val newUrl = requestUrl.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_TS, ts)
            .addQueryParameter(QUERY_PARAMETER_API_KEY, publicKey)
            .addQueryParameter(QUERY_PARAMETER_HASH, hash)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }
}