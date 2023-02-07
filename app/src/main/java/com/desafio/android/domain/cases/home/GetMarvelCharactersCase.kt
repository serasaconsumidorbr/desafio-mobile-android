package com.desafio.android.domain.cases.home

import com.desafio.android.core.base.execution.CaseResult
import com.desafio.android.domain.entity.MarvelCharacter
import com.desafio.android.repository.home.HomeRepository
import javax.inject.Inject

class GetMarvelCharactersCase @Inject constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(
        offset: Int
    ): CaseResult<List<MarvelCharacter>> = try {
        CaseResult.Success(
            repository.getMarvelCharacters(
                offset = offset
            )
        )

    } catch (exception: Exception) {
        CaseResult.Failure(exception)
    }
}