package com.example.marvel_app.utils.factory

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.features.characters.domain.model.Character

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
}