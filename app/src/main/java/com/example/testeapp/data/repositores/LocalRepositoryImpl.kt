package com.example.testeapp.data.repositores

import com.example.testeapp.data.CharacterDao
import com.example.testeapp.model.MarvelCharacter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepositoryImpl @Inject constructor(private val characterDao: CharacterDao) : LocalRepository {
    override fun getCharactersCache(): List<MarvelCharacter> {
        return characterDao.getAll()
    }

    override fun saveCharacters(characters: List<MarvelCharacter>) {
        characterDao.insertAll(characters)
    }

    override fun saveCharacter(character: MarvelCharacter) {
        characterDao.insert(character)
    }

    suspend override fun updateCharacter(character: MarvelCharacter) {
        characterDao.update(character)
    }

    override fun deleteAll() {
        characterDao.deleteAll()
    }
}