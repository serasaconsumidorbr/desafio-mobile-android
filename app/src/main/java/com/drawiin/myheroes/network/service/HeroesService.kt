package com.drawiin.myheroes.network.service

import com.drawiin.myheroes.network.model.response.GetHeroesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timeStamp: Int
    ): GetHeroesResponse
}