package com.example.marvelapp.framework.service

import com.example.marvelapp.features.characterslist.data.dto.CharacterDataWrapper
import com.example.marvelapp.features.comics.data.dto.ComicsDataWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("nameStartsWith") nameStartsWith: String?,
    ): CharacterDataWrapper

    @GET("characters/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: Int
    ): CharacterDataWrapper

    @GET("characters/{characterId}/comics")
    suspend fun getComics(
        @Path("characterId") characterId: Int
    ): ComicsDataWrapper
}