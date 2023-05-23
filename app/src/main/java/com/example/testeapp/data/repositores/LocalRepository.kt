package com.example.testeapp.data.repositores

import com.example.testeapp.model.MarvelCharacter

interface LocalRepository {
    fun getCharactersCache(): List<MarvelCharacter>
    fun saveCharacters(characters: List<MarvelCharacter>)
    fun saveCharacter(character: MarvelCharacter)
    suspend fun updateCharacter(character: MarvelCharacter)
    fun deleteAll()
}