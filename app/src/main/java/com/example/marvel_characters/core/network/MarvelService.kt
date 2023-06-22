package com.example.marvel_characters.core.network

import com.example.marvel_characters.data.dto.CharacterDataWrapperDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface MarvelService {

    @GET("v1/public/characters")
    fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<CharacterDataWrapperDTO>

}