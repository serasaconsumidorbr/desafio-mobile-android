package com.example.core.features.details.usecase

import com.example.core.features.details.data.repository.ComicRepository
import com.example.core.features.details.domain.Comic
import com.example.core.utils.ResultStatus
import com.example.core.utils.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetComicsUseCase {

    operator fun invoke(params: GetComicsParams): Flow<ResultStatus<List<Comic>>>

    data class GetComicsParams(val characterId: Int)
}

class GetComicsUseCaseImpl @Inject constructor(
    private val repository: ComicRepository
) : GetComicsUseCase, UseCase<GetComicsUseCase.GetComicsParams, List<Comic>>() {

    override suspend fun doWork(
        params: GetComicsUseCase.GetComicsParams
    ): ResultStatus<List<Comic>> {
        val comic = repository.getComics(params.characterId)
        return ResultStatus.Success(comic)
    }
}
