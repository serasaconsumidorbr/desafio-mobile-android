package com.developer.marvel.infrastructure.datasources

import com.developer.marvel.infrastructure.dto.CharacterDto

interface CharacterDataSource{

    suspend fun getCharacters(limit: Int): List<CharacterDto>
}