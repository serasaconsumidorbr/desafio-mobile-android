package com.example.marvelapp.features.characterslist.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelapp.features.characterslist.data.dto.CharacterDetails
import com.example.marvelapp.framework.service.ApiClient
import com.example.marvelapp.utils.PAGE_INDEX_INC_DEC
import retrofit2.HttpException
import java.io.IOException

class CharactersPagingSource(
    private val apiClient: ApiClient,
    private val query: String
) : PagingSource<Int, CharacterDetails>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDetails> {
        val offset = params.key ?: 0
        val limit = params.loadSize
        return try {

            val response = apiClient.getCharacters(
                limit = limit,
                offset = offset,
                nameStartsWith = query.ifEmpty { null }
            )

            var characters = response.data.results

            val nextOffset = offset + limit
            val nextKey = if (characters.isEmpty()) null else nextOffset

            LoadResult.Page(
                data = characters,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception.fillInStackTrace())
        } catch (exception: HttpException) {
            LoadResult.Error(exception.fillInStackTrace())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDetails>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(PAGE_INDEX_INC_DEC)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(PAGE_INDEX_INC_DEC)
        }
    }
}