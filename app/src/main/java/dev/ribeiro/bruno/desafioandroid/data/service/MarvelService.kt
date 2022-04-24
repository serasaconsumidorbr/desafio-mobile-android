package dev.ribeiro.bruno.desafioandroid.data.service

import dev.ribeiro.bruno.desafioandroid.core.API_KEY
import dev.ribeiro.bruno.desafioandroid.core.API_KEY_MD5_HASH
import dev.ribeiro.bruno.desafioandroid.data.model.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getMarvelCharacters(
        @Query("ts") ts: String = "1",
        @Query("offset") offset: Int? = null,
        @Query("apikey") apikey: String = API_KEY,
        @Query("hash") hash: String = API_KEY_MD5_HASH
    ) : CharacterResponse
}