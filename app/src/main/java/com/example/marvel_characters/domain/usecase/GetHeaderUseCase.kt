package com.example.marvel_characters.domain.usecase

import com.example.marvel_characters.data.dto.CharacterDTO
import com.example.marvel_characters.data.dto.ImageDTO
import com.example.marvel_characters.data.repository.CharacterRepository
import com.example.marvel_characters.domain.model.Character
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetHeaderUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
    operator fun invoke(
        headerSize: Int,
    ): Single<Result> {
        return repository.retrieveCharacters(0)
            .map {
                when (it) {
                    is CharacterRepository.Response.Success -> mapToSuccessToResult(
                        it.characters,
                        headerSize
                    )

                    is CharacterRepository.Response.Error -> Result.Error
                    is CharacterRepository.Response.EndOfList -> Result.Error
                }
            }
    }

    private fun mapToSuccessToResult(characters: List<CharacterDTO>, headerSize: Int): Result {
        val items = characters.filter { it.id != null }
            .take(headerSize)
            .map {
                Character(
                    id = it.id ?: 0,
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
        object Error : Result()
    }
}