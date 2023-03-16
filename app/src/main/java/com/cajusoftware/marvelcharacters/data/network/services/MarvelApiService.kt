package com.cajusoftware.marvelcharacters.data.network.services

import com.cajusoftware.marvelcharacters.data.network.responses.CharacterDataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    @GET("characters")
    suspend fun getCharacters(@Query("offset") offset: Int): CharacterDataWrapperResponse
}