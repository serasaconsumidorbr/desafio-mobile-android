package com.desafio.android.repository.home.sources

import com.desafio.android.domain.entity.MarvelCharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelCharacterApi {
    @GET("v1/public/characters")
    suspend fun getMarvelCharacters(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") orderBy: String,
    ): MarvelCharacterDataWrapper
}
