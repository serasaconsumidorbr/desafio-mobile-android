package com.desafio.marvel.feature.characters.data.api

import com.desafio.marvel.feature.characters.domain.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CharactersService {

    @GET("characters")
    fun getCharacters(@Query("limit") limit: Int,
                 @Query("offset") offset: Int):
            Call<DataResponse>
}