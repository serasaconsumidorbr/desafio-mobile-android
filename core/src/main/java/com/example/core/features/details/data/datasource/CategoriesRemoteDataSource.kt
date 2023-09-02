package com.example.core.features.details.data.datasource

import com.example.core.features.details.domain.Comic
import com.example.core.features.details.domain.Event

interface CategoriesRemoteDataSource {

    suspend fun fetchComics(characterId: Int): List<Comic>

    suspend fun fetchEvents(characterId: Int): List<Event>
}