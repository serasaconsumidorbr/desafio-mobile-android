package com.example.marvel_characters.repository

import com.example.marvel_characters.database.CharactersLocalDataSource
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.network.CharactersRemoteDataSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class Repository private constructor(
    private val charactersLocalDataSource: CharactersLocalDataSource,
    private val characterRemoteDataSource: CharactersRemoteDataSource
) {

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getRepository(
            charactersLocalDataSource: CharactersLocalDataSource,
            characterRemoteDataSource: CharactersRemoteDataSource
        ): Repository {
            return INSTANCE ?: synchronized(this) {
                Repository(charactersLocalDataSource, characterRemoteDataSource).also {
                    INSTANCE = it
                }
            }
        }
    }


    fun observeSavedCharactersList() = charactersLocalDataSource.observeCharactersList()
    fun observeSavedCharacter(id: String) = charactersLocalDataSource.observeCharacter(id)

    suspend fun getSavedCharacter(id: String, forceUpdate: Boolean = false) =
        charactersLocalDataSource.getCharacter(id)

    suspend fun saveCharacter(character: MarvelCharacter) {
        coroutineScope {
            launch { charactersLocalDataSource.saveCharacter(character) }
        }
    }

    suspend fun deleteCharacter(url: String) {
        coroutineScope {
            launch { charactersLocalDataSource.deleteCharacter(url) }
        }
    }


    suspend fun getCharactersFromWeb() = characterRemoteDataSource.getCharacters()


}
