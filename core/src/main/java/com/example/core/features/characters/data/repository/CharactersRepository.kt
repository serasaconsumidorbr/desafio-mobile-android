package com.example.core.features.characters.data.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.core.features.characters.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(query: String): PagingSource<Int, Character>

    fun getCachedCharacters(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Character>>
}