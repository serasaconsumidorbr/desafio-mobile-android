package com.br.leandro.marvel_hero_app.data.remote

import com.br.leandro.marvel_hero_app.data.remote.model.ApiResponse
import com.br.leandro.marvel_hero_app.data.remote.model.HeroResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun requestHeroes(
        @Query("limit") limit: Int = 5,
        @Query("offset") offset: Int = 0
    ): ApiResponse<HeroResponse>
}