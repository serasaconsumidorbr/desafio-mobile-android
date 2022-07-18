package com.ncz.desafio_mobile_android.external.service


import com.ncz.desafio_mobile_android.infrastructure.dto.ResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("/v1/public/characters")
    suspend fun getCharacter(
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): ResponseApi
}