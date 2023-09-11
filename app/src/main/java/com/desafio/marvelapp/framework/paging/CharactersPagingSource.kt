package com.desafio.marvelapp.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.desafio.marvelapp.framework.network.response.DataWrapperResponse
import com.desafio.marvelapp.framework.network.response.toCharacterModel
import com.project.core.data.repository.ICharactersRemoteDataSource
import com.project.core.domain.model.Character


private const val FIRST = 5
class CharactersPagingSource(
    private val remoteDataSource: ICharactersRemoteDataSource<DataWrapperResponse>,
    private val query: String
): PagingSource<Int, Character>() {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val offset = params.key ?: FIRST
            val queries = hashMapOf(
                "offset" to offset.toString()
            )
            if(query.isNotEmpty()){
                queries["nameStartsWith"] = query
            }

            val response = remoteDataSource.fetchCharacters(queries)

            val responseOffset = response.data.offset
            val totalCharacters= response.data.total

            LoadResult.Page(
                data = response.data.results.map { it.toCharacterModel() },
                prevKey = null,
                nextKey = if(offset < totalCharacters){
                    responseOffset + LIMIT
                } else null
            )

        } catch (exception: Exception){
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}