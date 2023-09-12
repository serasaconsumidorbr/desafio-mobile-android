package com.example.marvelapp.features.characterslist.data.repository

import androidx.paging.PagingData
import com.example.marvelapp.features.characterslist.data.dto.CharacterDetails
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getCharacters(query: String): Flow<PagingData<CharacterDetails>>
}