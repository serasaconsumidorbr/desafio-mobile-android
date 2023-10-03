package com.marvelverse

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marvelverse.data.Result
import com.marvelverse.data.repositories.InMemoryMarvelCharactersRepository
import com.marvelverse.domain.MarvelCharacter
import com.marvelverse.utils.getOrAwaitValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class MarvelVerseRepositoryShould {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun return_some_marvel_characters() {
        val marvelCharactersRepository = InMemoryMarvelCharactersRepository()

        val marvelCharacters: Result<List<MarvelCharacter>>? =
            marvelCharactersRepository.marvelCharacters.getOrAwaitValue()

        val characters: List<MarvelCharacter> = (marvelCharacters as Result.Success).data
        assertThat(characters).isNotEmpty
    }

    @Test
    fun return_some_marvel_characters_with_name_and_thumbnail() {
        val marvelCharactersRepository = InMemoryMarvelCharactersRepository()

        val marvelCharacters: Result<List<MarvelCharacter>>? =
            marvelCharactersRepository.marvelCharacters.getOrAwaitValue()

        val characters: List<MarvelCharacter> = (marvelCharacters as Result.Success).data
        assertThat(characters).isNotEmpty
        characters.forEach {
            assertThat(it.characterName).isNotEmpty
            assertThat(it.thumbnailImage).isNotNull
        }
    }
}

