package com.developer.marvel.repository

import com.developer.marvel.infrastructure.datasources.CharacterDataSource
import com.developer.marvel.infrastructure.repositories.CharacterRepositoryImpl
import com.developer.marvel.utils.mockers.GetCharactersDtoMocker
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
class CharacterRepositoryTest {
    private val datasource = Mockito.mock(CharacterDataSource::class.java)
    private val repository = CharacterRepositoryImpl(datasource)

    @Test
    fun `deve retornar a listagem de personagens`() {
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(this.testScheduler)
            Dispatchers.setMain(testDispatcher)


            Mockito.`when`(datasource.getCharacters(Mockito.anyInt()))
                .thenReturn(GetCharactersDtoMocker.getCharactersMocker())

            Assert.assertNotNull(repository.getCharacters())

            Mockito.verify(datasource, Mockito.times(1))
                .getCharacters(Mockito.anyInt())
        }
    }
}