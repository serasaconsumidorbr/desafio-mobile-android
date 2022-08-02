package com.developer.marvel.usecases

import com.developer.marvel.domain.failures.InvalidCredentialsFailure
import com.developer.marvel.domain.failures.MissingParameterFailure
import com.developer.marvel.domain.repositories.CharacterRepository
import com.developer.marvel.domain.usecases.CharacterUseCases
import com.developer.marvel.infrastructure.exceptions.InvalidCredentialsException
import com.developer.marvel.infrastructure.exceptions.MissingParameterException
import com.developer.marvel.utils.mockers.GetCharactersMocker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

@Suppress("BlockingMethodInNonBlockingContext")
@ExperimentalCoroutinesApi
class CharacterUseCasesTest {
    private val repository = Mockito.mock(CharacterRepository::class.java)
    private val useCases: CharacterUseCases = CharacterUseCases(repository = repository)

    @Test
    fun `deve retornar a listagem de personagens`() {
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(this.testScheduler)
            Dispatchers.setMain(testDispatcher)

            Mockito.`when`(repository.getCharacters(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(GetCharactersMocker.getCharactersMocker())

            Assert.assertNotNull(useCases.getCharacters(1, 10))

            Mockito.verify(repository, Mockito.times(1)).getCharacters(1, 10)
        }
    }

    @Test(expected = MissingParameterFailure::class)
    fun `deve retornar failure Missing Parameters`() {
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(this.testScheduler)
            Dispatchers.setMain(testDispatcher)

            Mockito.`when`(repository.getCharacters(Mockito.anyInt(), Mockito.anyInt()))
                .thenThrow(MissingParameterFailure::class.java)

            Assert.assertNotNull(useCases.getCharacters(1, 10))

            Mockito.verify(repository, Mockito.times(1)).getCharacters(1, 10)
        }
    }

    @Test(expected = InvalidCredentialsFailure::class)
    fun `deve retornar failure Invalid Credentials`() {
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(this.testScheduler)
            Dispatchers.setMain(testDispatcher)

            Mockito.`when`(repository.getCharacters(Mockito.anyInt(), Mockito.anyInt()))
                .thenThrow(InvalidCredentialsFailure::class.java)

            Assert.assertNotNull(useCases.getCharacters(1, 10))

            Mockito.verify(repository, Mockito.times(1)).getCharacters(1, 10)
        }
    }
}