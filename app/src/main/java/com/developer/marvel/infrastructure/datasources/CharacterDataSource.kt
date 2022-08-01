package com.developer.marvel.infrastructure.datasources

import com.developer.marvel.infrastructure.dto.CharacterDto

interface CharacterDataSource{

    suspend fun getCharacters(page: Int, limit: Int): List<CharacterDto>
}