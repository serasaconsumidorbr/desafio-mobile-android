package com.challenge.marvelcharacters.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterInterface {

    @GET("/v1/public/characters")
    suspend fun list(@Query("offset") offset: Int? = 0): Response<com.challenge.marvelcharacters.model.ResponseDto>

    @GET("/v1/public/characters")
    suspend fun list(
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): Response<com.challenge.marvelcharacters.model.ResponseDto>

}
