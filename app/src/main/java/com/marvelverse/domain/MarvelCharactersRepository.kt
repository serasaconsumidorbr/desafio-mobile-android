package com.marvelverse.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marvelverse.data.Result

interface MarvelCharactersRepository {

    val marvelCharacters: MutableLiveData<Result<List<MarvelCharacter>>>
    val totalCharacters: LiveData<Int>
    val favoriteCharacters: LiveData<List<MarvelCharacter>>

    suspend fun retrieveCharactersFrom(
        characterName: String?,
        numberOfLoadedCharacters: Int = 0,
    )

    fun addToFavorites(marvelCharacter: MarvelCharacter)
    fun removeFromFavorites(marvelCharacter: MarvelCharacter)
    fun isFavorite(marvelCharacter: MarvelCharacter): Boolean
}