package com.drawiin.myheroes.network

import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String
    )
}