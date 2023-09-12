package com.example.marvelapp.features.comics.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvelapp.features.comics.data.model.ComicDetailModel
import com.example.marvelapp.features.comics.data.repository.ComicsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val comicsRepository: ComicsRepository
) : ViewModel() {

    suspend fun getComics(characterId: Int): Flow<PagingData<ComicDetailModel>> {
        return comicsRepository.getComics(characterId).cachedIn(viewModelScope)
    }
}