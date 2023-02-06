package com.example.marvelheroes.data.api

import com.example.marvelheroes.data.dto.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersServiceApi {

    @GET("/v1/public/characters")
    fun getCharactersAsync(
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Deferred<ApiResponse>
}