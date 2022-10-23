package com.example.home_domain.mapper

import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_domain.model.HomeCarouselUiState
import com.example.util.Resource

interface ResultHomeCarouselToUiStateMapper {
    fun map(result: Resource<List<CharacterHomeUiModel>>): HomeCarouselUiState
}
