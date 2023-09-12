package com.example.marvelapp.features.comics.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelapp.features.comics.data.mapper.toComicDetailModel
import com.example.marvelapp.features.comics.data.model.ComicDetailModel
import com.example.marvelapp.framework.service.ApiClient
import com.example.marvelapp.utils.PAGE_INDEX_INC_DEC
import retrofit2.HttpException
import java.io.IOException

class ComicsPagingSource(
    private val apiClient: ApiClient,
    private val characterId: Int
) : PagingSource<Int, ComicDetailModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComicDetailModel> {
        val offset = params.key ?: 0
        val limit = params.loadSize
        return try {

            val response = apiClient.getComics(
                characterId = characterId
            )

            val comics = response.data.results
            val comicsList = comics.map {
                it.toComicDetailModel()
            }

            val nextOffset = offset + limit
            val nextKey = if (comicsList.isEmpty()) null else nextOffset

            LoadResult.Page(
                data = comicsList,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception.fillInStackTrace())
        } catch (exception: HttpException) {
            LoadResult.Error(exception.fillInStackTrace())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ComicDetailModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(PAGE_INDEX_INC_DEC)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(PAGE_INDEX_INC_DEC)
        }
    }
}