package br.com.marvelcomics.base.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object {
        const val API_PARAM = "&apikey=f89e0976b36b4668a3055bf7c60700de"
        const val HASH_PARAM = "&hash=c1eb1260f6d338e26fd8db96def570a4"
        const val TS_PARAM = "&ts="
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val newUrl = buildString {
            append(request.url)
            append(API_PARAM)
            append(HASH_PARAM)
            append("${TS_PARAM}1636296072")
        }

        request = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(request)
    }
}