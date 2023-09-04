package com.example.core.features.characters.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.features.characters.data.repository.CharactersRepository
import com.example.core.features.characters.domain.model.Character
import com.example.core.utils.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCharactersUseCase {

    operator fun invoke(
        params: GetCharactersParams
    ): Flow<PagingData<Character>>

    data class GetCharactersParams(val query: String, val pagingConfig: PagingConfig)

}

class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : PagingUseCase<GetCharactersUseCase.GetCharactersParams, Character>(), GetCharactersUseCase {

    override fun createFlowObservable(params: GetCharactersUseCase.GetCharactersParams): Flow<PagingData<Character>> {
       return charactersRepository.getCachedCharacters(params.query, params.pagingConfig)
    }
}