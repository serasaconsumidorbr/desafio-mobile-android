package com.cajusoftware.marvelcharacters.data.repositories

import com.cajusoftware.marvelcharacters.data.database.sources.ModelsPagingMediator
import com.cajusoftware.marvelcharacters.utils.asCharacterDto
import com.cajusoftware.tests.fakes.FakeCharacterDao
import com.cajusoftware.tests.fakes.FakeData.carouselCharacterList
import com.cajusoftware.tests.fakes.FakeData.character
import com.cajusoftware.tests.fakes.FakeData.characterDataWrapperResponse
import com.cajusoftware.tests.fakes.FakeMarvelApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CharacterRepositoryTest {

    private val fakeCharacterDao = FakeCharacterDao()
    private val fakeMarvelApiService = FakeMarvelApiService()
    private val repository = CharacterRepositoryImpl(
        fakeCharacterDao,
        ModelsPagingMediator(fakeCharacterDao, fakeMarvelApiService)
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun startFetchCharacters() = runTest {
        fakeCharacterDao.insertCharacters(characterDataWrapperResponse.data?.results!!.map {
            it.asCharacterDto(characterDataWrapperResponse.copyright, 1)
        })
        repository.getCharactersRandomly()
    }

    @After
    fun deleteOldData() {
        fakeCharacterDao.clear()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun characterRepository_getCharacterById_verifyCharacter() = runTest {
        repository.fetchCharacter(0)
        assertEquals(character, repository.character.first())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun characterRepository_getUpperCarouselCharacters_verifyListSize() = runTest {
        repository.getCharactersRandomly()
        assertEquals(2, repository.upperCarouselCharacters.first().size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun characterRepository_getUpperCarouselCharacters_verifyCarouselCharacter() = runTest {
        repository.getCharactersRandomly()
        assert(repository.upperCarouselCharacters.first().containsAll(carouselCharacterList))
    }
}