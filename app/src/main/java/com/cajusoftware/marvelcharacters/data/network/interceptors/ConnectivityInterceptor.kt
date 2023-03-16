package com.cajusoftware.marvelcharacters.data.network.interceptors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.cajusoftware.marvelcharacters.data.network.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (isConnected().not()) throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    private fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}