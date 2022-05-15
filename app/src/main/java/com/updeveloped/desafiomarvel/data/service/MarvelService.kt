package com.updeveloped.desafiomarvel.data.service

import com.updeveloped.desafiomarvel.core.API_KEY_PUBLICA
import com.updeveloped.desafiomarvel.data.response.dto.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") timeStamp: Int,
        @Query("offset") offset: Int? = null,
        @Query("apikey") apikey: String = API_KEY_PUBLICA,
        @Query("limit") limit: Int,
        @Query("hash") hashMd5: String,
    ) : CharacterResponse
}