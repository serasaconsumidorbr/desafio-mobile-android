package com.example.home_presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.paging.cachedIn
import com.example.home_domain.repository.HomeListRepository
import com.example.view_model.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeListViewModel @Inject constructor(
    private val repository: HomeListRepository,
) : BaseViewModel<HomeListUiEvent, HomeListUiState>(HomeListUiState.Loading) {

    init {
        getCharacters()
    }

    override fun dispatchEvent(event: HomeListUiEvent) {
        when(event) {
            is HomeListUiEvent.RetryLoad -> getCharacters()
        }
    }

    private fun getCharacters() {
        updateUiState {
            HomeListUiState.Success(
                data = repository
                    .getCharactersList()
                    .cachedIn(viewModelScope)
            )
        }
    }
}