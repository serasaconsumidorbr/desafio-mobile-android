package com.example.home_domain.usecase.impl

import com.example.home_domain.model.CharacterHomeUiModel
import com.example.home_domain.repository.HomeCarouselRepository
import com.example.home_domain.usecase.GetHomeCarouselUseCase
import com.example.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomeCarousel @Inject constructor(
    private val repository: HomeCarouselRepository,
) : GetHomeCarouselUseCase {

    companion object {
        private const val COULD_NOT_LOAD_DATA_MESSAGE = "Couldn't load data"
    }

    override fun invoke(): Flow<Resource<List<CharacterHomeUiModel>>> = flow {
        emit(Resource.Loading())
        emit(getPosLoadingResource())
    }

    private suspend fun getPosLoadingResource() = repository.getHomeCarouselCharacters()?.let {
        Resource.Success(it)
    } ?: Resource.Error(COULD_NOT_LOAD_DATA_MESSAGE)
}
