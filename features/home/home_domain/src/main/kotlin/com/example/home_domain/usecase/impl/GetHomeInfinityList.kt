package com.example.home_domain.usecase.impl

import androidx.paging.PagingData
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_domain.repository.HomeListRepository
import com.example.home_domain.usecase.GetHomeInfinityListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeInfinityList @Inject constructor(
    private val repository: HomeListRepository,
) : GetHomeInfinityListUseCase {

    override fun invoke(): Flow<PagingData<CharacterHomeUiModel>> =
        repository.getCharactersList().flow
}