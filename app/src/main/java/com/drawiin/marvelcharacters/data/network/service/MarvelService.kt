package com.drawiin.marvelcharacters.data.network.service

import com.drawiin.marvelcharacters.data.network.model.response.GetCharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timeStamp: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<GetCharactersResponse>
}