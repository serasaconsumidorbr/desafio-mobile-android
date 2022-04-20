package com.example.home_domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.home_domain.model.CharacterHomeUiModel
import kotlinx.coroutines.flow.Flow

interface HomeListRepository {
    fun getCharactersList(): Pager<Int, CharacterHomeUiModel>
}