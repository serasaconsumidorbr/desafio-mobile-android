package com.example.home_data.remote.datasource

import androidx.paging.PagingState
import com.example.home_data.remote.HomeListApi
import com.example.home_data.remote.mapper.CharacterDtoToCharacterMapper
import com.example.home_domain.model.Character
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class HomeListDataSourceImpl @Inject constructor(
    private val api: HomeListApi,
    private val mapper: CharacterDtoToCharacterMapper,
) : HomeListDataSource() {

    companion object {
        private const val CHARACTERS_LIMIT = 25
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val key = params.key
            val result = api.getList(
                limit = CHARACTERS_LIMIT,
                offset = key ?: 0
            )
            val resultMapped = result.map { mapper.mapFrom(it) }
            LoadResult.Page(
                data = resultMapped,
                prevKey = key,
                nextKey = params.key ?: CHARACTERS_LIMIT
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = null
}