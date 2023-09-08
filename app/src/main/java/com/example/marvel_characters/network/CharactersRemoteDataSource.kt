package com.example.marvel_characters.network

import com.example.marvel_characters.Result
import com.example.marvel_characters.domain.MarvelCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRemoteDataSource(private val characterService: MarvelApiService) {
    private var lastResultDataInfo: CharactersResultDataInfo? = null

    suspend fun getNextCharacterPage(): Result<List<MarvelCharacter>> =
        if (lastResultDataInfo != null && lastResultDataInfo!!.hasNextPage()) {
            getCharacterFromSearch(lastResultDataInfo!!.nextPageOffset())
        } else {
            getCharacterFromSearch()
        }


    private suspend fun getCharacterFromSearch(
        offset: Int = 0,
        limit: Int = 100
    ): Result<List<MarvelCharacter>> {
        return withContext(Dispatchers.IO) {
            val characterListContainer =
                characterService.getCharacters(offset = offset, limit = limit)

            characterListContainer.let {
                if (it.isSuccessful) {
                    lastResultDataInfo = it.body()!!.data.run {
                        CharactersResultDataInfo(offset = offset, limit = limit, total = total)
                    }

                    Result.Success(it.body()!!.asDomainModel())
                } else {
                    Result.Error(Exception(it.errorBody().toString()))
                }
            }
        }
    }

    fun hasNextPage() = lastResultDataInfo?.hasNextPage()?:true

}


class InternetMissingException : Exception()