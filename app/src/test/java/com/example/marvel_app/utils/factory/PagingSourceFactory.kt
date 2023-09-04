package com.example.marvel_app.utils.factory

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.features.characters.domain.model.Character
import kotlinx.coroutines.flow.Flow

class PagingSourceFactory {

    fun create(heroes: List<Character>) = object : PagingSource<Int, Character>(){
        override fun getRefreshKey(state: PagingState<Int, Character>) = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
            return LoadResult.Page(
                data = heroes,
                prevKey = null,
                nextKey = 20
            )
        }
    }

    fun createPagingData(heroes: List<Character>): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                create(heroes)
            }
        ).flow
    }
}