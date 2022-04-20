package com.example.home_presentation.components.list

import androidx.paging.PagingData
import com.example.home_domain.model.CharacterHomeUiModel
import kotlinx.coroutines.flow.Flow

sealed interface HomeListUiState {
    object Loading: HomeListUiState
    object Error: HomeListUiState
    data class Success(
        val data: Flow<PagingData<CharacterHomeUiModel>>
    ): HomeListUiState
}