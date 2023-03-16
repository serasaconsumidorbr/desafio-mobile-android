package com.cajusoftware.marvelcharacters.data.repositories

import androidx.paging.PagingData
import com.cajusoftware.marvelcharacters.data.domain.CarouselCharacter
import com.cajusoftware.marvelcharacters.data.domain.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    val carouselCharacters: Flow<PagingData<CarouselCharacter>>
    val upperCarouselCharacters: Flow<List<CarouselCharacter>>
    val character: Flow<Character?>
    suspend fun fetchCharacter(characterId: Int)
    suspend fun getCharactersRandomly()
}