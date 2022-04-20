package com.example.home_domain.usecase

import androidx.paging.PagingData
import com.example.home_domain.model.CharacterHomeUiModel
import kotlinx.coroutines.flow.Flow

interface GetHomeInfinityListUseCase {
    operator fun invoke(): Flow<PagingData<CharacterHomeUiModel>>
}