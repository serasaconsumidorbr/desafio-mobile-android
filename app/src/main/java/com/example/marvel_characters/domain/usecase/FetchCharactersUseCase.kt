package com.example.marvel_characters.domain.usecase

import com.example.marvel_characters.data.dto.CharacterDTO
import com.example.marvel_characters.data.dto.ImageDTO
import com.example.marvel_characters.data.repository.CharacterRepository
import com.example.marvel_characters.domain.model.Character
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
    private var currentPage = 0

    operator fun invoke(
        removeIds: Set<Int> = setOf(),
    ): Single<Result> {
        return repository.retrieveCharacters(currentPage)
            .doOnSuccess { currentPage += 1 }
            .map {
                when (it) {
                    is CharacterRepository.Response.Success -> mapToSuccessResult(
                        it.characters,
                        removeIds
                    )

                    is CharacterRepository.Response.EndOfList -> Result.EndOfList
                    is CharacterRepository.Response.Error -> Result.Error
                }
            }
    }

    private fun mapToSuccessResult(
        characters: List<CharacterDTO>,
        blockedIds: Set<Int>
    ): Result.Success {
        val items = characters.mapNotNull {
            if (it.id == null || blockedIds.contains(it.id))
                null
            else
                Character(
                    id = it.id,
                    name = it.name.orEmpty(),
                    description = it.description.orEmpty(),
                    thumbnail = mapImageUrl(it.thumbnail)
                )
        }
        return Result.Success(items)
    }

    private fun mapImageUrl(image: ImageDTO?): String {
        val path = if (image?.path?.startsWith("http") == true) {
            image.path.replaceFirst("http", "https")
        } else (image?.path.orEmpty())
        return "$path.${image?.extension}"
    }

    sealed class Result {
        data class Success(val characters: List<Character>) : Result()
        object EndOfList : Result()
        object Error : Result()
    }
}