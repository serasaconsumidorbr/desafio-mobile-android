package com.example.marvel_characters.network


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.ContextCompat
import com.example.marvel_characters.BuildConfig.HASH
import com.example.marvel_characters.BuildConfig.PUBLIC_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelApiService {

    @GET("v1/public/characters?ts=1&apikey=$PUBLIC_API_KEY&hash=$HASH")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): Response<NetworkCharacterContainer>

    @GET("v1/public/characters/{id}?ts=1&apikey=$PUBLIC_API_KEY&hash=$HASH")
    suspend fun getCharacterById(
        @Path("id") id: String
    ): Response<NetworkCharacterContainer>

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