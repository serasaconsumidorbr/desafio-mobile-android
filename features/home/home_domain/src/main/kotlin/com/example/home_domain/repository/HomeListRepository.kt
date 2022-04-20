package com.example.home_domain.repository

import androidx.paging.PagingData
import com.example.home_domain.model.CharacterHomeUiModel
import kotlinx.coroutines.flow.Flow

interface HomeListRepository {
    fun getCharactersList(): Flow<PagingData<CharacterHomeUiModel>>
}