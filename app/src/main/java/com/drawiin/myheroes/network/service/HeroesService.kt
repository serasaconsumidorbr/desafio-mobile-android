package com.drawiin.myheroes.network.service

import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String
    )
}