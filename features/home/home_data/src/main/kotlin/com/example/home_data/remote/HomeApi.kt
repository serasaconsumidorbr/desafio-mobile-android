package com.example.home_data.remote

import com.example.home_data.remote.dto.CharactersDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): CharactersDto

}