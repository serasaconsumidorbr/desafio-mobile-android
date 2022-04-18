package com.example.home_data.remote

import com.example.home_data.remote.dto.CharactersDto
import com.example.util.Api
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeListApi {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): CharactersDto

}