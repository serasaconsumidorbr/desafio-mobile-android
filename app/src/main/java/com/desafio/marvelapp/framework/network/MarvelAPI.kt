package com.desafio.marvelapp.framework.network

import com.desafio.marvelapp.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelAPI {

    @GET("characters")
    suspend fun getCharacters(@QueryMap query: Map<String, String>): DataWrapperResponse
}