package com.example.home_presentation.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.paging.cachedIn
import com.example.home_domain.repository.HomeListRepository
import com.example.home_presentation.HomeBaseViewModel
import com.example.home_presentation.screen.HomeScreenUiEvent
import com.example.view_model.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeListViewModel @Inject constructor(
    private val repository: HomeListRepository,
) : HomeBaseViewModel<HomeListUiState>(HomeListUiState.Loading) {

    init {
        getCharacters()
    }

    override fun getCharacters() {
        updateUiState {
            HomeListUiState.Success(
                data = repository
                    .getCharactersList()
                    .cachedIn(viewModelScope)
            )
        }
    }
}