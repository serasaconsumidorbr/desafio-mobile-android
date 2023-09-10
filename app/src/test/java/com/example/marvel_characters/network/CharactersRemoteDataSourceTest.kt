package com.example.marvel_characters.network

import com.example.marvel_characters.Result
import com.example.marvel_characters.Samples
import com.example.marvel_characters.di.appModule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent

class CharactersRemoteDataSourceTest {
    private val remoteDataSource: CharactersRemoteDataSource by KoinJavaComponent.inject(
        CharactersRemoteDataSource::class.java
    )
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        startKoin { loadKoinModules(appModule) }
        mockWebServer = MockWebServer()
    }

    @After
    fun tearDown() {
        GlobalContext.stopKoin()
        mockWebServer.shutdown()
    }


    @Test
    fun shouldReturnNonEmptyCharactersListResult(): Unit = runBlocking {

        val resultData = remoteDataSource.getNextCharacterPage()
        assertTrue(resultData is Result.Success)
        assertTrue((resultData as Result.Success).data.isNotEmpty())

    }

    @Test
    fun shouldReturnExpectCharactersData(): Unit = runBlocking {

        val resultData = remoteDataSource.getCharacterById(Samples.characterWithCompleteData.id)
        assertTrue(resultData is Result.Success)

        val returnedCharacter = (resultData as Result.Success).data

        assertEquals(Samples.characterWithCompleteData, returnedCharacter)
    }
}