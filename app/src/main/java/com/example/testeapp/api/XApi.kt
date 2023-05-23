package com.example.testeapp.api

import com.example.testeapp.model.ListResponse
import retrofit2.Response
import com.example.testeapp.model.MarvelCharacter
import retrofit2.http.GET
import retrofit2.http.Query

interface XApi {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") query: String = "86fbd060c28b8796cd096fc98f7214c2",
        @Query("hash") hash: String,
        @Query("ts") timeStamp: String,
        @Query("offset") offSet: Int,
    ): Response<ListResponse>

}