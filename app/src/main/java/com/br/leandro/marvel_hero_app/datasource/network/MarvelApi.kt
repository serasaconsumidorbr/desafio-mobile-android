package com.br.leandro.marvel_hero_app.datasource.network

import com.br.leandro.marvel_hero_app.datasource.network.model.Characters
import com.br.leandro.marvel_hero_app.datasource.network.model.Comics
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.*

interface MarvelApi {

    @GET("/v1/public/characters")
    fun getCharacters(@Query("offset") offset: Int): Flowable<Characters>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacterById(@Path("characterId") characterId: Int): Single<Characters>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getComicsByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("limit") limit: Int = 2
    ): Flowable<Comics>
}