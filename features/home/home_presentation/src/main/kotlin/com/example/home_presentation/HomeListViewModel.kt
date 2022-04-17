package com.example.home_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.home_domain.repository.HomeListRepository
import javax.inject.Inject

@HiltViewModel
class HomeListViewModel @Inject constructor(
    private val repository: HomeListRepository
): ViewModel() {
    fun getCharacters(): Flow<PagingData<Character>> = repository
        .getCharactersList()
        .cachedIn(viewModelScope)
}