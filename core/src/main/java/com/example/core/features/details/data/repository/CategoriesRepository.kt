package com.example.core.features.details.data.repository

import com.example.core.features.details.domain.Comic
import com.example.core.features.details.domain.Event

interface CategoriesRepository {

    suspend fun getComics(characterId: Int): List<Comic>

    suspend fun getEvent(characterId: Int): List<Event>
}