package com.example.home_data.remote.datasource

import android.util.Log
import androidx.paging.PagingState
import com.example.home_data.remote.HomeListApi
import com.example.home_data.remote.HomeListRepositoryImpl
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.example.home_domain.model.Character
import okio.IOException
import retrofit2.HttpException

class HomeListDataSourceImpl(
    private val api: HomeListApi,
    private val mapper: CharactersDataDtoToCharactersMapper,
) : HomeListDataSource() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> = try {
        val key = params.key
        val response = api.getAllCharacters(
            limit = (key ?: STARTING_PAGE_INDEX) * HomeListRepositoryImpl.PAGE_SIZE,
            offset = key ?: 0
        )
        val charactersUi = mapper.mapFrom(response.data)
        LoadResult.Page(
            data = charactersUi,
            prevKey = key,
            nextKey = params.key?.plus(1) ?: STARTING_PAGE_INDEX
        )
    } catch (e: IOException) {
        LoadResult.Error(e)
    } catch (e: HttpException) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = null
}