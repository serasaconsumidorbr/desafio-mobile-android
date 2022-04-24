package dev.ribeiro.bruno.desafioandroid.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.ribeiro.bruno.desafioandroid.data.service.MarvelService

class PageDataSource(
    private val marvelService: MarvelService
    ) : PagingSource<Int, CharacterDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDetail> {
        try {
            val currentLoadingPageKey = params.key ?: 0
            val response = dev.ribeiro.bruno.desafioandroid.data.toEntity()
            val responseData = mutableListOf<CharacterDetail>()
            val data = response.results ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == ITEM_PER_PAGE) null else currentLoadingPageKey - ITEM_PER_PAGE

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(ITEM_PER_PAGE)
            )
        }catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDetail>): Int? {
        TODO("Not yet implemented")
    }

    companion object{
        private const val ITEM_PER_PAGE = 20
    }

}