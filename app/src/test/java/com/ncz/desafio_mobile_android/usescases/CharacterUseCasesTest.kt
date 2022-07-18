package com.ncz.desafio_mobile_android.domain.usescases

import com.ncz.desafio_mobile_android.domain.repositories.InterfaceCharacterRepository
import com.ncz.desafio_mobile_android.mockers.getCharactersEntityResponseMock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

@Suppress("BlockingMethodInNonBlockingContext")
@ExperimentalCoroutinesApi
class CharacterUseCasesTest {
    private val repository: InterfaceCharacterRepository = Mockito.mock(InterfaceCharacterRepository::class.java)
    private val useCases: CharacterUseCases = CharacterUseCases(repository = repository)

    @Before
    fun setUp() {

    }

    @Test
    fun `should return list characters`() {
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(this.testScheduler)
            with(Dispatchers) {
                setMain(testDispatcher)
            }

            Mockito.`when`(repository.getCharacter()).thenReturn(getCharactersEntityResponseMock())

            Assert.assertNotNull(useCases.getCharacter())

            Mockito.verify(repository, Mockito.times(1)).getCharacter()

        }
    }
}