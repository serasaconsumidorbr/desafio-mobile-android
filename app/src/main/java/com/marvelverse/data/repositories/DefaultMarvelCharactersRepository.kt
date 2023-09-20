package com.marvelverse.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marvelverse.data.Result
import com.marvelverse.data.datasources.FavoritesDbDataSource
import com.marvelverse.data.datasources.RemoteDataSource
import com.marvelverse.data.network.MarvelAPI
import com.marvelverse.domain.MarvelCharacter
import com.marvelverse.domain.MarvelCharactersRepository
import javax.inject.Inject

class DefaultMarvelCharactersRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val favoritesDbDataSource: FavoritesDbDataSource,
    private val marvelAPI: MarvelAPI,
) : MarvelCharactersRepository {
    override val marvelCharacters = MutableLiveData<Result<List<MarvelCharacter>>>()

    private val _totalCharacters = MutableLiveData(0)
    override val totalCharacters: LiveData<Int> = _totalCharacters

    private val _favoriteCharacters = favoritesDbDataSource.favoriteCharacters
    override val favoriteCharacters: LiveData<List<MarvelCharacter>> = _favoriteCharacters

    override fun addToFavorites(marvelCharacter: MarvelCharacter) {
        favoritesDbDataSource.addToFavorites(marvelCharacter)
    }

    override fun removeFromFavorites(marvelCharacter: MarvelCharacter) {
        favoritesDbDataSource.removeFromFavorites(marvelCharacter)
    }

    override fun isFavorite(marvelCharacter: MarvelCharacter): Boolean {
        return favoritesDbDataSource.exists(marvelCharacter)
    }


    override suspend fun retrieveCharactersFrom(
        characterName: String?,
        numberOfLoadedCharacters: Int,
    ) {
        marvelCharacters.postValue(Result.Loading)

        kotlin.runCatching {
            remoteDataSource.getCharacters(
                marvelAPI.timestamp.toString(),
                marvelAPI.apiKey,
                marvelAPI.hash,
                numberOfLoadedCharacters,
                characterName
            )
        }
            .onSuccess { marvelAPIResponseDTO ->
                marvelCharacters.postValue(Result.Success(marvelAPIResponseDTO.getCharacters()))
                _totalCharacters.postValue(marvelAPIResponseDTO.data.total.toInt())
            }
            .onFailure { error: Throwable ->
                marvelCharacters.postValue(Result.Error(error))
            }
    }
}