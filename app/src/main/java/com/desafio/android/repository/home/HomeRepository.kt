package com.desafio.android.repository.home

import com.desafio.android.domain.entity.MarvelCharacter

interface HomeRepository {
    suspend fun getMarvelCharacters(
        offset: Int
    ): List<MarvelCharacter>
}