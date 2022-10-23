package com.example.home_presentation.components.list

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.home_domain.usecase.GetHomeInfinityListUseCase
import com.example.home_presentation.HomeBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeListViewModel @Inject constructor(
    private val getHomeInfinityList: GetHomeInfinityListUseCase,
) : HomeBaseViewModel<HomeListUiState>(HomeListUiState.Loading) {

    init {
        getCharacters()
    }

    override fun getCharacters() {
        updateUiState {
            HomeListUiState.Success(
                data = getHomeInfinityList().cachedIn(viewModelScope)
            )
        }
    }
}
