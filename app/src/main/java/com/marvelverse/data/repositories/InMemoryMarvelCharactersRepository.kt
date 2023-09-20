package com.marvelverse.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.marvelverse.data.Result
import com.marvelverse.domain.MarvelCharacter
import com.marvelverse.domain.MarvelCharactersRepository
import com.marvelverse.domain.ThumbnailImage
import kotlinx.coroutines.flow.Flow

class InMemoryMarvelCharactersRepository : MarvelCharactersRepository {

    private val sampleCharacters = listOf(
        MarvelCharacter("Captain America", ThumbnailImage("urlExample/CaptainAmerica", "png")),
        MarvelCharacter("Wonder Woman", ThumbnailImage("urlExample/WonderWoman", "png")),
        MarvelCharacter("Hulk", ThumbnailImage("urlExample/Hulk", "png")),
        MarvelCharacter("Black Panther", ThumbnailImage("urlExample/Black Panther", "png")),
    )

    override val marvelCharacters: MutableLiveData<Result<List<MarvelCharacter>>> =
        MutableLiveData(Result.Success(sampleCharacters))
    override val totalCharacters: LiveData<Int> = MutableLiveData()
    override val favoriteCharacters: LiveData<List<MarvelCharacter>> = MutableLiveData()

    override suspend fun retrieveCharactersFrom(
        characterName: String?,
        numberOfLoadedCharacters: Int,
    ) {
        marvelCharacters.postValue(Result.Success(sampleCharacters))
    }

    override fun addToFavorites(marvelCharacter: MarvelCharacter) {
        TODO("Not yet implemented")
    }

    override fun removeFromFavorites(marvelCharacter: MarvelCharacter) {
        TODO("Not yet implemented")
    }

    override fun isFavorite(marvelCharacter: MarvelCharacter): Boolean {
        TODO("Not yet implemented")
    }
}