package com.example.marvelapp.features.comics.data.repository

import androidx.paging.PagingData
import com.example.marvelapp.features.comics.data.model.ComicDetailModel
import kotlinx.coroutines.flow.Flow

interface ComicsRepository {

    suspend fun getComics(characterId: Int): Flow<PagingData<ComicDetailModel>>
}