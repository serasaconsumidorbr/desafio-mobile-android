package com.cajusoftware.marvelcharacters.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.cajusoftware.marvelcharacters.data.network.ConnectivityStatus
import com.cajusoftware.marvelcharacters.data.network.ConnectivityStatus.OFFLINE
import com.cajusoftware.marvelcharacters.data.network.ConnectivityStatus.ONLINE
import com.cajusoftware.marvelcharacters.data.network.NoConnectivityException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class RetryCallback(val callback: () -> Unit) : AbstractCoroutineContextElement(RetryCallback) {
    companion object Key : CoroutineContext.Key<RetryCallback>
}

object NetworkUtils {
    var connectionErrorHandle: ((ConnectivityStatus) -> Unit)? = null

    private var coroutineContextList: MutableList<CoroutineContext> = mutableListOf()

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        when (throwable) {
            is NoConnectivityException -> {
                coroutineContextList.add(coroutineContext)
                connectionErrorHandle?.invoke(OFFLINE)
            }
            else -> Log.e("Network-Errors", throwable.message, throwable)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun networkConnectivity(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                connectionErrorHandle?.invoke(ONLINE)

                coroutineContextList.forEach {
                    it[RetryCallback.Key]?.callback?.invoke()
                }
                coroutineContextList.clear()
            }

            override fun onLost(network: Network) {
                connectionErrorHandle?.invoke(OFFLINE)
            }
        })
    }
}