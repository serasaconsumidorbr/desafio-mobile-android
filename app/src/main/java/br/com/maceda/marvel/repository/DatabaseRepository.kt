package br.com.maceda.marvel.repository

import br.com.maceda.marvel.data.dao.CharacterDao
import br.com.maceda.marvel.data.model.Character
import javax.inject.Inject

class DatabaseRepository @Inject constructor (
    private val characterDao: CharacterDao
) : CharacterRepository {

    override suspend fun getCharacters(offset: Int,
                              limit: Int,
                              orderBy: String): ResponseRepositoryResult<List<Character>> {
        return try {
            val characters = characterDao.list(offset, limit, orderBy)
            ResponseRepositoryResult.Success(characters)
        }catch(e: Exception){
            ResponseRepositoryResult.ErrorException(e)
        }

    }

}