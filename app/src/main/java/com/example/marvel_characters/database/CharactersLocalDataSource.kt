package com.example.marvel_characters.database

import com.example.marvel_characters.Result
import com.example.marvel_characters.database.entities.asDomainModel
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.network.asDatabaseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CharactersLocalDataSource internal constructor(
    private val marvelDao: MarvelDao,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun observeCharactersList(): Flow<Result<List<MarvelCharacter>>> {
        return marvelDao.observeCharacterList().map {
            Result.Success(it.asDomainModel())
        }
    }

    fun observeCharacter(url: String): Flow<Result<MarvelCharacter>> {
        return marvelDao.observeCharacterById(url).map {
            Result.Success(it.asDomainModel())
        }
    }

    suspend fun getCharacter(url: String): Result<MarvelCharacter> = withContext(ioDispatcher) {
        try {
            val character = marvelDao.getCharacterById(url)
            if (character != null) {
                return@withContext Result.Success(character.asDomainModel())
            } else {
                return@withContext Result.Error(Exception("MarvelCharacter not found!"))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    suspend fun saveCharacter(marvelCharacter: MarvelCharacter) = withContext(ioDispatcher) {
        marvelDao.insertCharacter(marvelCharacter.asDatabaseModel())
    }

    suspend fun deleteCharacterById(characterId: String) = withContext<Unit>(ioDispatcher) {
        marvelDao.deleteCharacterById(characterId)
    }
}
