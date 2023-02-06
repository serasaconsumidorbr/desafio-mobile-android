package com.example.marvelheroes.data.repositories

import com.example.marvelheroes.data.mock.MockCharactersResponse
import com.example.marvelheroes.data.mock.generateApiResponseMock
import com.example.marvelheroes.domain.repositories.ICharactersRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

const val TS_MOCK = "TS_MOCK"
const val KEY_MOCK = "KEY_MOCK"
const val HASH_MOCK = "HASH_MOCK"

class HeroesRepositoryTest {
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    private val characterResultMock = listOf(MockCharactersResponse())

    @Test
    fun getCharacters(): Unit = runBlocking {
        val charactersRepository = Mockito.mock(ICharactersRepository::class.java)
        val apiResponse = generateApiResponseMock()

         Mockito.`when`(charactersRepository.getCharactersAsync(1, TS_MOCK, KEY_MOCK, HASH_MOCK))
            .thenReturn(apiResponse)

        val charactersResult = charactersRepository.getCharactersAsync(1, TS_MOCK, KEY_MOCK, HASH_MOCK).data

        Assert.assertEquals(1, charactersResult.results?.count())

        Assert.assertEquals(charactersResult.results?.get(0)?.id, characterResultMock[0].id)
        Assert.assertEquals(charactersResult.results?.get(0)?.name, characterResultMock[0].name)
        Assert.assertEquals(
            charactersResult.results?.get(0)?.thumbnail?.path,
            characterResultMock[0].thumbnail.path
        )
    }
}
