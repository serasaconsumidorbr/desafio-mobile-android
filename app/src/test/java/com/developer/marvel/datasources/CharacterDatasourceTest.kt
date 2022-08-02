package com.developer.marvel.datasources

import com.developer.marvel.external.datasources.CharacterDataSourceImpl
import com.developer.marvel.infrastructure.exceptions.InvalidCredentialsException
import com.developer.marvel.infrastructure.exceptions.MissingParameterException
import com.developer.marvel.utils.MockServer
import com.developer.marvel.utils.mockers.GetCharactersJsonMocker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertFailsWith

@Suppress("BlockingMethodInNonBlockingContext")
@ExperimentalCoroutinesApi
class CharacterDatasourceTest {

    @Test
    fun `deve retornar a listagem de personagens`() {
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            Dispatchers.setMain(testDispatcher)

            val json = GetCharactersJsonMocker.`200`.getCharactersResponse()
            val server = MockServer.start(200, json)

            val retrofit = Retrofit.Builder()
                .baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val datasource = CharacterDataSourceImpl(retrofit)
            val characters = datasource.getCharacters(1, 10)

            Assert.assertNotNull(characters)

            MockServer.stop()
        }
    }

    @Test(expected = MissingParameterException::class)
    fun `deve retornar error 409 Missing Parameters`() {
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            Dispatchers.setMain(testDispatcher)

            val json = GetCharactersJsonMocker.`409`.getCharactersResponseMissingParameters()
            val server = MockServer.start(409, json)

            val retrofit = Retrofit.Builder()
                .baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val datasource = CharacterDataSourceImpl(retrofit)
            val characters =  datasource.getCharacters(1, 10)

            Assert.assertNotNull(characters)

            MockServer.stop()
        }
    }

    @Test(expected = InvalidCredentialsException::class)
    fun `deve retornar error 401 Invalid Credentials`() {
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            Dispatchers.setMain(testDispatcher)

            val json = GetCharactersJsonMocker.`401`.getCharactersResponseInvalidCredentials()
            val server = MockServer.start(401, json)

            val retrofit = Retrofit.Builder()
                .baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val datasource = CharacterDataSourceImpl(retrofit)
            val characters =  datasource.getCharacters(1, 10)

            Assert.assertNotNull(characters)

            MockServer.stop()
        }
    }
}