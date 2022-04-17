package com.example.home_data.remote

import com.example.home_data.remote.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeListApi {

    @GET("v1/public/characters")
    fun getList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("apikey") apiKey: String = com.example.util.Keys.API
    ): List<CharacterDto>

    companion object {
        const val BASE_URL = "http://gateway.marvel.com/"
    }
}