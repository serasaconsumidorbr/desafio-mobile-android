package com.example.marvelapp.features.comics.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvelapp.features.comics.data.model.ComicDetailModel
import com.example.marvelapp.features.comics.data.paging.ComicsPagingSource
import com.example.marvelapp.framework.service.ApiClient
import com.example.marvelapp.utils.PAGE_PREFETCH_DISTANCE
import com.example.marvelapp.utils.PAGING_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicsRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient
) : ComicsRepository {

    override suspend fun getComics(characterId: Int): Flow<PagingData<ComicDetailModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGING_PAGE_SIZE,
                prefetchDistance = PAGE_PREFETCH_DISTANCE
            ),
            pagingSourceFactory = { ComicsPagingSource(apiClient, characterId) }
        ).flow
    }
}