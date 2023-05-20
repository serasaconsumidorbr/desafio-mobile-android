package br.com.marvelcomics.base.interceptor

import br.com.marvelcomics.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object {
        const val API_PARAM = "&apikey="
        const val HASH_PARAM = "&hash="
        const val TS_PARAM = "&ts="
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val marvelApiKey = BuildConfig.API_KEY
        val newUrl = buildString {
            append(request.url)
            append("$API_PARAM${BuildConfig.API_KEY}")
            append("$HASH_PARAM${BuildConfig.HASH_KEY}")
            append("$TS_PARAM${BuildConfig.TIMESTAMP_KEY}")
        }

        request = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(request)
    }
}