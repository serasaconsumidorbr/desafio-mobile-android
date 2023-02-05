package com.example.marvelheroes.domain

import com.example.marvelheroes.data.mock.MockCharactersResponse
import com.example.marvelheroes.domain.repositories.ICharactersRepository
import com.example.marvelheroes.domain.services.HeroesService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

class HeroesServiceTest {

    @Test
    fun getCharacters(): Unit = runBlocking {
        val charactersRepository = Mockito.mock(ICharactersRepository::class.java)
        val heroesService = HeroesService(charactersRepository)

        val character = MockCharactersResponse()
        Mockito.`when`(heroesService.invoke(Unit)).thenReturn(listOf(character))

    }
}

private fun <T> OngoingStubbing<T>.thenReturn(listOf: List<MockCharactersResponse>): OngoingStubbing<T> {
    TODO("Not yet implemented")
}
