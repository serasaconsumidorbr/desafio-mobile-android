package com.example.marvelapp.features.characterslist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvelapp.features.characterslist.data.dto.CharacterDetails
import com.example.marvelapp.features.characterslist.data.paging.CharactersPagingSource
import com.example.marvelapp.framework.service.ApiClient
import com.example.marvelapp.utils.PAGE_PREFETCH_DISTANCE
import com.example.marvelapp.utils.PAGING_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient
) : HomeRepository {

    override suspend fun getCharacters(query: String): Flow<PagingData<CharacterDetails>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGING_PAGE_SIZE,
                prefetchDistance = PAGE_PREFETCH_DISTANCE
            ),
            pagingSourceFactory = { CharactersPagingSource(apiClient, query) }
        ).flow
    }
}