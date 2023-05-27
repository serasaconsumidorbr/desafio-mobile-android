package com.victorvgc.mymarvelheros.home.ui.heroes_list

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.domain.domain.HeroesPage
import com.victorvgc.mymarvelheros.home.domain.use_case.GetHeroesPage

class HeroesPagingSource(private val getHeroesPage: GetHeroesPage) : PagingSource<Int, Hero>() {
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val nextPageNumber = params.key ?: 1
            val upcoming = if (nextPageNumber == 1) {
                getHeroesPage(HeroesPage.DEFAULT_INIT_PAGE_SIZE)
            } else {
                getHeroesPage(nextPageNumber * HeroesPage.DEFAULT_PAGE_SIZE)
            }

            val nextPage = if (upcoming.pageSize < HeroesPage.DEFAULT_PAGE_SIZE) {
                null
            } else {
                nextPageNumber + 1
            }

            LoadResult.Page(
                data = upcoming.heroesList,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = nextPage
            )

        } catch (e: Exception) {
            Log.e("LOAD MATCHES", e.stackTraceToString())
            LoadResult.Error(e)
        }
    }
}