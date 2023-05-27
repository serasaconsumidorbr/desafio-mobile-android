package br.com.marvelcomics.data.remote

import br.com.marvelcomics.data.dto.MarvelCharListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun fetchHeroes(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20,
        @Query("nameStartsWith") name: String? = null
    ) : MarvelCharListResponse
}