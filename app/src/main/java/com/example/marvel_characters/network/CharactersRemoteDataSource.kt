package com.example.marvel_characters.network

import com.example.marvel_characters.Result
import com.example.marvel_characters.domain.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRemoteDataSource(private val characterService: MarvelApiService) {
    private var lastListResultDataInfo: CharactersResultDataInfo? = null

    suspend fun getNextCharacterPage(): Result<List<Character>> =
        if (lastListResultDataInfo != null && lastListResultDataInfo!!.hasNextPage()) {
            getCharacters(lastListResultDataInfo!!.nextPageOffset())
        } else {
            getCharacters()
        }


    private suspend fun getCharacters(
        offset: Int = 0,
        limit: Int = 100
    ): Result<List<Character>> {
        return withContext(Dispatchers.IO) {
            try {
                val characterListContainer =
                    characterService.getCharacters(offset = offset, limit = limit)

                characterListContainer.let {
                    if (it.isSuccessful) {
                        lastListResultDataInfo = it.body()!!.data.run {
                            CharactersResultDataInfo(offset = offset, limit = limit, total = total)
                        }

                        Result.Success(it.body()!!.asDomainModel())
                    } else {
                        throw Exception(it.errorBody().toString())
                    }
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
    }

    fun hasNextPage() = lastListResultDataInfo?.hasNextPage() ?: true

    suspend fun getCharacterById(
        id: String,
    ): Result<Character> {
        return withContext(Dispatchers.IO) {
            try {

                val characterContainer =
                    characterService.getCharacterById(id)
                val singleElementIndex = 0
                characterContainer.let {
                    if (it.isSuccessful) {
                        Result.Success(it.body()!!.asDomainModel()[singleElementIndex])
                    } else {
                        Result.Error(Exception(it.errorBody().toString()))
                    }
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
    }
}