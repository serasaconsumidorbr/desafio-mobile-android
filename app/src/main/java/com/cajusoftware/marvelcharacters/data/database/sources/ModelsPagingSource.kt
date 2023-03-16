package com.cajusoftware.marvelcharacters.data.database.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cajusoftware.marvelcharacters.BuildConfig
import com.cajusoftware.marvelcharacters.data.database.dao.CharacterDao
import com.cajusoftware.marvelcharacters.data.database.dtos.CharacterDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

private const val STARTING_PAGING_INDEX = 0

class ModelsPagingSource(
    private val characterDao: CharacterDao
) : PagingSource<Int, CharacterDto>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        val pageIndex = params.key ?: STARTING_PAGING_INDEX

        return withContext(Dispatchers.IO) {
            try {

                val result = characterDao.getCharactersForPaging(pageIndex)

                LoadResult.Page(
                    data = result,
                    prevKey = if (pageIndex == STARTING_PAGING_INDEX) null else pageIndex,
                    nextKey = if (result.isEmpty()) null else pageIndex + BuildConfig.PAGE_SIZE
                )
            } catch (e: IOException) {
                LoadResult.Error(e)
            }
        }
    }
}