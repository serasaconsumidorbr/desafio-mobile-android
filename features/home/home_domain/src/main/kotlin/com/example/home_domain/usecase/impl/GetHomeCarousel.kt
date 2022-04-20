package com.example.home_domain.usecase.impl

import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_domain.repository.HomeCarouselRepository
import com.example.home_domain.usecase.GetHomeCarouselUseCase
import com.example.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomeCarousel @Inject constructor(
    private val repository: HomeCarouselRepository
): GetHomeCarouselUseCase {
    override fun invoke(): Flow<Resource<List<CharacterHomeUiModel>>> = flow {
        emit(Resource.Loading())
        repository.getHomeCarouselCharacters()?.let {
            emit(Resource.Success(it))
        } ?: emit(Resource.Error("Couldn't load data"))
    }
}