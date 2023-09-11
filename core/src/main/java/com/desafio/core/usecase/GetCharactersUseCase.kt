package com.project.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.core.data.repository.ICharactersRepository
import com.project.core.domain.model.Character
import com.project.core.usecase.IGetCharactersUseCase.GetCharactersParams
import com.project.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface IGetCharactersUseCase{
    operator fun invoke(params: GetCharactersParams): Flow<PagingData<Character>>
    suspend operator fun invoke(limit: Int): Flow<List<Character>>

    data class GetCharactersParams(
        val query: String,
        val pagingConfig: PagingConfig
    )
}

class GetCharactersUseCaseImpl @Inject constructor(
    private val charaRepository: ICharactersRepository
): PagingUseCase<GetCharactersParams, Character>(), IGetCharactersUseCase {

    override fun createFlowObservable(params: GetCharactersParams): Flow<PagingData<Character>> {
        val pagingSource = charaRepository.getCharacters(params.query)
        return Pager(config = params.pagingConfig){
            pagingSource
        }.flow
    }
    override suspend fun createFlowObservable(params: Int): Flow<List<Character>> {
        return flowOf(charaRepository.getCarouselCharacters(params))
    }
}