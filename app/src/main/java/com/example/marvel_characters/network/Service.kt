package com.example.marvel_characters.network


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.marvel_characters.BuildConfig.PUBLIC_API_KEY
import androidx.core.content.ContextCompat
import retrofit2.Response
import retrofit2.http.GET


interface MarvelApiService {
    @GET("v1/public/v1/public/characters?apikey=".plus(PUBLIC_API_KEY))
    suspend fun getCharacters(): Response<NetworkCharacterContainer>

}

fun isInternetAvailable(context: Context): Boolean {
    val cm = ContextCompat.getSystemService(context, ConnectivityManager::class.java)

    val capabilities = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm?.getNetworkCapabilities(cm.activeNetwork) ?: return false
    } else {
        TODO("VERSION.SDK_INT < M")
    }

    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)


}