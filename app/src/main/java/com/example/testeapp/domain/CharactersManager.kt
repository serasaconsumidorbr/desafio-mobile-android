package com.example.testeapp.domain

import com.example.testeapp.data.repositores.LocalRepository
import com.example.testeapp.data.repositores.RemoteRepository
import com.example.testeapp.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.example.testeapp.common.areListsEqual
import com.example.testeapp.model.MarvelCharacter
import javax.inject.Inject

class CharactersManager @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) {

    suspend fun getChracters(offSet: Int): Flow<Result<Any>> {
        return flow {
            emit(Result.inProgress())
            val cache = localRepository.getCharactersCache()
            val remote = remoteRepository.fetchCharacters(offSet)

            if (remote.status == Result.Status.SUCCESS) {
                remote.data?.let {
                    val marvelCharacters = it.data.result

                    if (!cache.areListsEqual(marvelCharacters)) {
                        val updatedWithFavorites = updateFavorites(cache, marvelCharacters)
                        localRepository.deleteAll()
                        localRepository.saveCharacters((updatedWithFavorites + cache).distinct())
                        emit(Result.success(updatedWithFavorites))
                    } else
                        emit(Result.success(cache))
                }
            } else {
                if (!cache.isNullOrEmpty())
                    emit(Result.success(cache))
                else
                    emit(remote)
            }


        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateChracter(character: MarvelCharacter) {
        localRepository.updateCharacter(character)
    }

    fun updateFavorites(
        charactersA: List<MarvelCharacter>,
        charactersB: List<MarvelCharacter>
    ): List<MarvelCharacter> {
        return charactersB.map { characterB ->
            val characterA = charactersA.find { it.name == characterB.name }
            if (characterA != null) {
                characterB.copy(isFavorite = characterA.isFavorite)
            } else {
                characterB
            }
        }
    }

}
