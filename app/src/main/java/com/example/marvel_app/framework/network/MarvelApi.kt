package com.example.marvel_app.framework.network

import com.example.marvel_app.features.characters.response.CharacterResponse
import com.example.marvel_app.features.detail.response.ComicResponse
import com.example.marvel_app.features.detail.response.EventResponse
import com.example.marvel_app.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse<CharacterResponse>

    @GET("characters/{characterId}/comics")
    suspend fun getComics(
        @Path("characterId")
        characterId: Int
    ): DataWrapperResponse<ComicResponse>

    @GET("characters/{characterId}/events")
    suspend fun getEvents(
        @Path("characterId")
        characterId: Int
    ): DataWrapperResponse<EventResponse>
}