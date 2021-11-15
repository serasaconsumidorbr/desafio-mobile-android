package br.com.maceda.marvel.data.remote

import br.com.maceda.marvel.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("characters")
    suspend fun list(
        @Query("offset") offSet: Int = 0,
        @Query("limit") limit: Int = 10,
        @Query("orderBy") orderBy: String = "name"
    ): Response<CharacterResponse>

}