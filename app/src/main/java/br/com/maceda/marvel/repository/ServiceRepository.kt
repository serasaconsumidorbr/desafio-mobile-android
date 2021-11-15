package br.com.maceda.marvel.repository

import br.com.maceda.marvel.data.model.Character
import br.com.maceda.marvel.data.remote.CharacterService
import br.com.maceda.marvel.exceptions.NetworkException
import java.lang.Exception
import javax.inject.Inject

class ServiceRepository @Inject constructor (
    private val api: CharacterService
) : CharacterRepository {

    override suspend fun getCharacters(offset: Int,
                              limit: Int,
                              orderBy: String): ResponseRepositoryResult<List<Character>> {
        return try {
            val response = api.list(offset, limit, orderBy)
            if (response.isSuccessful){
                var characterList = listOf<Character>()
                response.body()?.let {
                    characterList = it.data.results
                }
                ResponseRepositoryResult.Success(characterList)
            }else{
                val messageError = "${response.code()} ${response.message()}"
                ResponseRepositoryResult.Error(messageError)
            }
        }catch (e: Exception){
            ResponseRepositoryResult.ErrorException(NetworkException())
        }

    }

}