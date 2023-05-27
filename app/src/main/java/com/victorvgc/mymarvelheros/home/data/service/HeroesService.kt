package com.victorvgc.mymarvelheros.home.data.service

import com.victorvgc.mymarvelheros.home.data.model.remote.RemoteHeroResponse
import com.victorvgc.mymarvelheros.home.data.utils.GET_CHARACTERS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesService {

    @GET(GET_CHARACTERS)
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<RemoteHeroResponse>
}