package com.example.marvel_characters.network

import com.example.marvel_characters.domain.MarvelCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.marvel_characters.Result

class CharactersRemoteDataSource(private val characterService: MarvelApiService) {
    suspend fun getCharacters(): Result<List<MarvelCharacter>> {
        return getCharacterFromSearch()
    }

    private suspend fun getCharacterFromSearch(): Result<List<MarvelCharacter>> {
        return withContext(Dispatchers.IO) {
            val characterListContainer = characterService.getCharacters()

            characterListContainer.let {
                if (it.isSuccessful) {
                    Result.Success(it.body()!!.asDomainModel())
                } else {
                    Result.Error(Exception(it.errorBody().toString()))
                }
            }
        }
    }
}


class InternetMissingException : Exception()