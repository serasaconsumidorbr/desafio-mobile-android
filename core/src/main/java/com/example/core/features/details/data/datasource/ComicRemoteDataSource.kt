package com.example.core.features.details.data.datasource

import com.example.core.features.details.domain.Comic

interface ComicRemoteDataSource {

    suspend fun fetchComics(characterId: Int): List<Comic>
}