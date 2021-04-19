package com.challenge.marvelcharacters.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.challenge.marvelcharacters.model.Character
import com.challenge.marvelcharacters.network.CharacterInterface
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 5

class CharactersDataSource(private val service : CharacterInterface) :

    PagingSource<Int, Character>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.list(position ).body()
            val items = response?.data?.result ?: emptyList()
            val nextKey = if (items.isEmpty()){
                null
            } else {
                position + params.loadSize
            }

            LoadResult.Page(
                data = items,
                prevKey = if(position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        }catch (exception : IOException){
            return  LoadResult.Error(exception)
        }catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
