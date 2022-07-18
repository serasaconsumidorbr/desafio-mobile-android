package com.ncz.desafio_mobile_android.datasources

import com.ncz.desafio_mobile_android.external.datasource.CharacterDataSource
import com.ncz.desafio_mobile_android.mockers.getCharactersAPIResponseMock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("BlockingMethodInNonBlockingContext")
@ExperimentalCoroutinesApi
class CharacterDatasourceTest {
    @Before
    fun setUp() {

    }

    @Test
    fun `should return list characters`() {
                runTest {
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            Dispatchers.setMain(testDispatcher)

            val server = MockWebServer()
            server.start()
            server.enqueue(MockResponse().setResponseCode(200).setBody(getCharactersAPIResponseMock()))

            val retrofit = Retrofit.Builder()
                .baseUrl(server.url(""))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val datasource = CharacterDataSource(retrofit)
            val characters = datasource.getCharacter(1, "", "", "")

            Assert.assertNotNull(characters)

            server.shutdown()
        }
    }
}