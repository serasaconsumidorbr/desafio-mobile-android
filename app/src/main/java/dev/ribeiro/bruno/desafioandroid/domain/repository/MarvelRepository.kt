package dev.ribeiro.bruno.desafioandroid.domain.repository

import androidx.paging.PagingData
import dev.ribeiro.bruno.desafioandroid.domain.entities.CharacterDetail
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    suspend fun getMarvelCharacters() : Flow<PagingData<CharacterDetail>>

}