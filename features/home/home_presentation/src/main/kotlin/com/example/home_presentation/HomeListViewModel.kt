package com.example.home_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.home_domain.repository.HomeListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeListViewModel @Inject constructor(
    private val repository: HomeListRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeListUiState>(HomeListUiState.Loading)
    val state: StateFlow<HomeListUiState> = _state

    init {
        getCharacters()
    }

    private fun getCharacters() {
        _state.update {
            HomeListUiState.Success(
                data = repository
                    .getCharactersList()
                    .cachedIn(viewModelScope)
            )
        }
    }
}