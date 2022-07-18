package com.ncz.desafio_mobile_android.repository

import com.ncz.desafio_mobile_android.domain.repositories.InterfaceCharacterRepository
import com.ncz.desafio_mobile_android.infrastructure.datasources.InterfaceCharacterDataSource
import com.ncz.desafio_mobile_android.infrastructure.repository.CharacterRepository
import com.ncz.desafio_mobile_android.mockers.getCharactersDtoResponseMock
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
class CharacterRepositoryTest {
    private val datasource: InterfaceCharacterDataSource = Mockito.mock(InterfaceCharacterDataSource::class.java)
    private val repository: InterfaceCharacterRepository = CharacterRepository(datasource)

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

            Mockito.`when`(datasource.getCharacter(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(getCharactersDtoResponseMock())

            Assert.assertNotNull(repository.getCharacter())

            Mockito.verify(datasource, Mockito.times(1))
                .getCharacter(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())

        }
    }
}