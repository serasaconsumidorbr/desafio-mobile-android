package com.example.marvel_characters.repository

import com.example.marvel_characters.database.CharactersLocalDataSource
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.network.CharactersRemoteDataSource

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
    suspend fun getSavedCharacter(id: String) = charactersLocalDataSource.getCharacter(id)
    suspend fun getSavedCharacterList() = charactersLocalDataSource.getSavedCharacters()
    suspend fun saveCharacter(character: MarvelCharacter) =
        charactersLocalDataSource.saveCharacter(character)

    suspend fun deleteCharacter(character: MarvelCharacter) =
        charactersLocalDataSource.deleteCharacterById(character.id)

    suspend fun getNextPage() = characterRemoteDataSource.getNextCharacterPage()
    suspend fun getCharacterByIdFromWeb(id: String) = characterRemoteDataSource.getCharacterById(id)
    fun hasNextPage() = characterRemoteDataSource.hasNextPage()
    suspend fun updateCharacter(marvelCharacter: MarvelCharacter) =
        charactersLocalDataSource.updateCharacter(marvelCharacter)

}
