package com.example.home_data.remote.datasource

import android.util.Log
import androidx.paging.PagingState
import com.example.home_data.remote.HomeListApi
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.example.home_domain.model.Character
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class HomeListDataSourceImpl @Inject constructor(
    private val api: HomeListApi,
    private val mapper: CharactersDataDtoToCharactersMapper,
    private val homePageConfig: HomePageConfig,
) : HomeListDataSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> = try {
        val key = params.key
        val limit = (key ?: homePageConfig.startingIndex) * homePageConfig.size
        val offset = key ?: 0
        val nextKey = key?.plus(homePageConfig.incrementValue) ?: homePageConfig.startingIndex
        val prevKey = key?.minus(homePageConfig.incrementValue)
        Log.d("HomeListDataSourceImpl", key.toString())
        val response = api.getAllCharacters(
            limit = limit,
            offset = offset
        )
        val charactersUi = mapper.mapFrom(response.data)
        LoadResult.Page(
            data = charactersUi,
            prevKey = prevKey,
            nextKey = nextKey
        )
    } catch (e: IOException) {
        LoadResult.Error(e)
    } catch (e: HttpException) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = state.anchorPosition
        ?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(
                homePageConfig.incrementValue
            ) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(
                homePageConfig.incrementValue
            )
        }
}