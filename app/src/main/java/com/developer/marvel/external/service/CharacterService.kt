package com.developer.marvel.external.service

import com.developer.marvel.infrastructure.dto.CharacterDto
import com.developer.marvel.infrastructure.dto.service.ResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Response<ResponseDto<CharacterDto>>
}