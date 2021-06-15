package com.luisedu.marvel_app.data.retrofit

import com.luisedu.marvel_app.model.MarvelApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiCall {

    @GET("characters")
    fun fetchCharactersList(
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apiKey: String,
        @Query("limit") limit: Int = 100
    ): Call<MarvelApiResponse>
}