package com.project.core.data.repository

import androidx.paging.PagingSource
import com.project.core.domain.model.Character

interface ICharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, Character>
    suspend fun getCarouselCharacters(limit: Int): List<Character>
}