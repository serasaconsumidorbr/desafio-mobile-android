package welias.marvel.data.provider

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Cache

class NetworkProvider {
    private val context = ContextProvider.currentContext
    private val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB

    fun isInternetAvailable(): Boolean {
        return if (context != null) {
            var isConnected = false // Initial Value
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected) {
                isConnected = true
            }

            isConnected
        } else false
    }

    val cache = if (context != null) Cache(context.cacheDir, cacheSize) else null
}
