package com.example.core.features.favorites.usecase

import com.example.core.features.characters.domain.model.Character
import com.example.core.features.favorites.data.repository.FavoritesRepository
import com.example.core.utils.CoroutinesDispatchers
import com.example.core.utils.ResultStatus
import com.example.core.utils.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RemoveFavoriteUseCase {

    operator fun invoke(params: ParamsRemoveFavorite): Flow<ResultStatus<Unit>>

    data class ParamsRemoveFavorite(val characterId: Int, val name: String, val imageUrl: String)
}

class RemoveFavoriteUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<RemoveFavoriteUseCase.ParamsRemoveFavorite, Unit>(), RemoveFavoriteUseCase {

    override suspend fun doWork(params: RemoveFavoriteUseCase.ParamsRemoveFavorite): ResultStatus<Unit> {
        return withContext(dispatchers.io()){
            favoritesRepository.deleteFavorite(
                Character(params.characterId, params.name, params.imageUrl)
            )

            ResultStatus.Success(Unit)
        }
    }
}