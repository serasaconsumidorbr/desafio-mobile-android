package com.example.marvel_app.framework.network

import com.example.core.feature.character.data.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse
}