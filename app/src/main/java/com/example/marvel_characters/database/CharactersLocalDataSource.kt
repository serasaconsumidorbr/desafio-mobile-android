package com.example.marvel_characters.database

import com.example.marvel_characters.Result
import com.example.marvel_characters.database.entities.asDomainModel
import com.example.marvel_characters.domain.Character
import com.example.marvel_characters.network.asDatabaseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CharactersLocalDataSource internal constructor(
    private val marvelDao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun observeCharactersList(): Flow<Result<List<Character>>> =
        try {
            marvelDao.observeCharacterList().map {
                Result.Success(it.asDomainModel())
            }
        } catch (exception: Exception) {
            flowOf(Result.Error(exception))
        }


    fun observeCharacter(url: String): Flow<Result<Character>> =
        try {
            marvelDao.observeCharacterById(url).map {
                Result.Success(it.asDomainModel())
            }
        } catch (exception: Exception) {
            flowOf(Result.Error(exception))
        }

    suspend fun getSavedCharacters(): Result<List<Character>> {
        return withContext(ioDispatcher) {
            try {
                val charactersList = marvelDao.getCharactersList().asDomainModel()
                Result.Success(charactersList)
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
    }

    suspend fun getCharacter(url: String): Result<Character> = withContext(ioDispatcher) {
        try {
            val character = marvelDao.getCharacterById(url)
            if (character != null) {
                return@withContext Result.Success(character.asDomainModel())
            } else {
                return@withContext Result.Error(NotFoundException())
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    suspend fun saveCharacter(character: Character) = withContext(ioDispatcher) {
        marvelDao.insertCharacter(character.asDatabaseModel())
    }

    suspend fun deleteCharacterById(characterId: String) = withContext<Unit>(ioDispatcher) {
        marvelDao.deleteCharacterById(characterId)
    }

    suspend fun updateCharacter(character: Character) {
        withContext(ioDispatcher) {
            marvelDao.updateCharacter(character.asDatabaseModel())
        }
    }
}

class NotFoundException : Exception("Item not found")
