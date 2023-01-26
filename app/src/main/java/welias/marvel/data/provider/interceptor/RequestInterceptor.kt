package welias.marvel.data.provider.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import welias.marvel.data.provider.NetworkProvider

var offlineInterceptor = Interceptor { chain ->
    var request: Request = chain.request()
    if (!NetworkProvider().isInternetAvailable()) {
        val maxStale = 5 // Offline cache available for 60 seconds
        request = request.newBuilder()
            .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
            .removeHeader("Pragma")
            .build()
    }
    chain.proceed(request)
}

var onlineInterceptor = Interceptor { chain ->
    val response = chain.proceed(chain.request())
    val maxAge = 60 // read from cache for 60 seconds even if there is internet connection
    response.newBuilder()
        .header("Cache-Control", "public, max-age=$maxAge")
        .removeHeader("Pragma")
        .build()
}
