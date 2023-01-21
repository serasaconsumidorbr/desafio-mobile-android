package com.example.data.heroes.remote

import com.example.data.heroes.remote.model.ResponseApi
import retrofit2.Response
import retrofit2.http.GET

interface HeroesApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters() : Response<ResponseApi>
}