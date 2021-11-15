package br.com.maceda.marvel.repository

import br.com.maceda.marvel.data.dao.CharacterDao
import br.com.maceda.marvel.data.model.Character
import javax.inject.Inject

class CacheRepository @Inject constructor (
    private val service: ServiceRepository,
    private val database: DatabaseRepository,
    private val characterDao: CharacterDao,
) : CharacterRepository {

    override suspend fun getCharacters(offset: Int,
                              limit: Int,
                              orderBy: String): ResponseRepositoryResult<List<Character>> {

        val resultDatabase = database.getCharacters(offset, limit, orderBy)
        if (resultDatabase is ResponseRepositoryResult.Success){
            var characterList = resultDatabase.value
            if (characterList.isEmpty()){
                val resultService = service.getCharacters(offset, limit, orderBy)
                if (resultService is ResponseRepositoryResult.Success) {
                    characterList = resultService.value
                    characterDao.insertAll(*characterList.toTypedArray())
                }else {
                    return resultService
                }
            }
            return ResponseRepositoryResult.Success(characterList)
        }else{
            return resultDatabase
        }

    }

}