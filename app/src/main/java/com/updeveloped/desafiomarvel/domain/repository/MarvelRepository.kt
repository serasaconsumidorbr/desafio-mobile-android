package com.updeveloped.desafiomarvel.domain.repository

import androidx.paging.PagingData
import com.updeveloped.desafiomarvel.domain.entities.CharacterDetail
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    suspend fun getMarvelCharactersPage() : Flow<PagingData<CharacterDetail>>
    suspend fun getMarvelCharacters() : List<CharacterDetail>
}