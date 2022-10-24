package com.example.marvel_characters.data.remote

import com.example.marvel_characters.domain.models.APIResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apikey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): APIResponse

}