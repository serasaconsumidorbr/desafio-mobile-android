package com.example.core.features.characters.data.repository

import androidx.paging.PagingSource
import com.example.core.features.characters.domain.model.Character

interface CharactersRepository {

    fun getCharacters(query: String): PagingSource<Int, Character>
}