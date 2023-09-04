package com.example.core.features.favorites.usecase

import com.example.core.features.characters.domain.model.Character
import com.example.core.features.favorites.data.repository.FavoritesRepository
import com.example.core.utils.CoroutinesDispatchers
import com.example.core.utils.ResultStatus
import com.example.core.utils.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AddFavoriteUseCase {

    operator fun invoke(params: ParamsAddFavorite): Flow<ResultStatus<Unit>>

    data class ParamsAddFavorite(val characterId: Int, val name: String, val imageUrl: String)
}

class AddFavoriteUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<AddFavoriteUseCase.ParamsAddFavorite, Unit>(), AddFavoriteUseCase {

    override suspend fun doWork(params: AddFavoriteUseCase.ParamsAddFavorite): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            favoritesRepository.saveFavorite(
                Character(params.characterId, params.name, params.imageUrl)
            )

            ResultStatus.Success(Unit)
        }
    }
}