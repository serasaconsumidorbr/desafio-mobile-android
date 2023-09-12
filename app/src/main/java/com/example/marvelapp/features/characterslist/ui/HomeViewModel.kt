package com.example.marvelapp.features.characterslist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvelapp.features.characterslist.data.dto.CharacterDetails
import com.example.marvelapp.features.characterslist.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    suspend fun getCharacters(query: String): Flow<PagingData<CharacterDetails>> {
        return homeRepository.getCharacters(query).cachedIn(viewModelScope)
    }
}