package com.victorvgc.mymarvelheros.home.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.recyclerview.widget.DiffUtil
import com.victorvgc.mymarvelheros.core.ui.BaseViewModel
import com.victorvgc.mymarvelheros.core.ui.UIState
import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.domain.use_case.GetHeroesPage
import com.victorvgc.mymarvelheros.home.domain.use_case.GetInitialHeroes
import com.victorvgc.mymarvelheros.home.ui.heroes_list.HeroesPagingSource
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getHeroesPage: GetHeroesPage,
    private val getInitialHeroes: GetInitialHeroes
) : BaseViewModel<List<Hero>>() {

    val heroesFlow = Pager(PagingConfig(1)) {
        HeroesPagingSource(getHeroesPage)
    }.flow.cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            stateFlow.tryEmit(UIState.Loading())

            val highlightHeroes = getInitialHeroes()

            stateFlow.tryEmit(UIState.Success(highlightHeroes))
        }
    }

    fun getHeroesComparator(): DiffUtil.ItemCallback<Hero> {
        return object : DiffUtil.ItemCallback<Hero>() {
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem == newItem
            }
        }
    }

}