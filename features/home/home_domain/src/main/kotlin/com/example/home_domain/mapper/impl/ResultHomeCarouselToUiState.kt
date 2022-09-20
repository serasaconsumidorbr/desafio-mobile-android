package com.example.home_domain.mapper.impl

import com.example.home_domain.mapper.ResultHomeCarouselToUiStateMapper
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_domain.model.HomeCarouselUiState
import com.example.util.Resource

class ResultHomeCarouselToUiState : ResultHomeCarouselToUiStateMapper {
    override fun map(result: Resource<List<CharacterHomeUiModel>>): HomeCarouselUiState =
        when (result) {
            is Resource.Success -> HomeCarouselUiState.Success(data = result.data)
            is Resource.Error -> HomeCarouselUiState.Error
            is Resource.Loading -> HomeCarouselUiState.Loading
        }
}
