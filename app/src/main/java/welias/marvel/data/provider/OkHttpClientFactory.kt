package welias.marvel.data.provider

import okhttp3.OkHttpClient
import welias.marvel.core.constants.DEFAULT_TIME_VALUE
import welias.marvel.data.provider.interceptor.offlineInterceptor
import welias.marvel.data.provider.interceptor.onlineInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {

    fun build(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .setupTimeout()
            .setupCache()
            .build()
    }

    private fun OkHttpClient.Builder.setupTimeout() = apply {
        readTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
        writeTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
        connectTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
    }

    private fun OkHttpClient.Builder.setupCache() = apply {
        cache(NetworkProvider().cache)
    }
}
