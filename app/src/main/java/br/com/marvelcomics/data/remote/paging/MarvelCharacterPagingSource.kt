package br.com.marvelcomics.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.marvelcomics.data.mapper.toMarvelCharacter
import br.com.marvelcomics.data.remote.MarvelApi
import br.com.marvelcomics.model.MarvelCharacter
import retrofit2.HttpException

class MarvelCharacterPagingSource(
    private val marvelApi: MarvelApi,
) : PagingSource<Int, MarvelCharacter>() {

    companion object {
        const val START_OFFSET = 0
    }

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        val offset = params.key ?: START_OFFSET

        return try {
            val response = marvelApi.fetchHeroes(limit = 20, offset = offset)
            val heroes = response.results.map { it.toMarvelCharacter() }

            LoadResult.Page(
                data = heroes,
                prevKey = if (offset == START_OFFSET) null else offset - 20,
                nextKey = if (heroes.isEmpty()) null else offset + 20
            )
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}