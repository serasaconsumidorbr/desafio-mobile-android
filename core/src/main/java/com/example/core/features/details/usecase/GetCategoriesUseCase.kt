package com.example.core.features.details.usecase

import com.example.core.features.details.data.repository.CategoriesRepository
import com.example.core.features.details.domain.Comic
import com.example.core.features.details.domain.Event
import com.example.core.utils.AppCoroutinesDispatchers
import com.example.core.utils.CoroutinesDispatchers
import com.example.core.utils.ResultStatus
import com.example.core.utils.UseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetCategoriesUseCase {

    operator fun invoke(params: GetCategoriesParams): Flow<ResultStatus<Pair<List<Comic>,List<Event>>>>

    data class GetCategoriesParams(val characterId: Int)
}

class GetCategoriesUseCaseImpl @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val dispatchers: CoroutinesDispatchers
) : GetCategoriesUseCase, UseCase<GetCategoriesUseCase.GetCategoriesParams, Pair<List<Comic>,List<Event>>>() {

    override suspend fun doWork(
        params: GetCategoriesUseCase.GetCategoriesParams
    ): ResultStatus<Pair<List<Comic>,List<Event>>> {
       return withContext(dispatchers.io()){
            val comicsDeferred = async { categoriesRepository.getComics(params.characterId) }
            val eventsDeferred = async { categoriesRepository.getEvent(params.characterId) }

            val comics = comicsDeferred.await()
            val events = eventsDeferred.await()

            ResultStatus.Success(comics to events)
        }
    }
}