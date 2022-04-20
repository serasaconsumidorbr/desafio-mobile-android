package com.example.home_domain.usecase

import com.example.home_domain.model.CharacterHomeUiModel
import com.example.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetHomeCarouselUseCase {
    operator fun invoke(): Flow<Resource<List<CharacterHomeUiModel>>>
}