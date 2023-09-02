package com.example.core.features.details.data.repository

import com.example.core.features.details.domain.Comic

interface ComicRepository {

    suspend fun getComics(characterId: Int): List<Comic>
}