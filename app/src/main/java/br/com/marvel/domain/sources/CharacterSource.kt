package br.com.marvel.domain.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.marvel.data.MarvelApi
import br.com.marvel.domain.models.Character
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1
private const val LIMIT = 20

class CharacterSource @Inject constructor(
    private val ts: String,
    private val apikey: String,
    private val hash: String,
    private val marvelApi: MarvelApi
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = marvelApi.getCharacters(ts, apikey, hash, LIMIT, LIMIT * page)
            val results = response.data?.results ?: emptyList()

            LoadResult.Page(
                data = results,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (results.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}