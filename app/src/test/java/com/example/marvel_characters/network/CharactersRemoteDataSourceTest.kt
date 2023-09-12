package com.example.marvel_characters.network

import com.example.marvel_characters.Result
import com.example.marvel_characters.Samples.characterWithCompleteData
import com.example.marvel_characters.TestWithKoinBase
import com.example.marvel_characters.di.appModule
import com.example.marvel_characters.domain.Character
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent

class CharactersRemoteDataSourceTest: TestWithKoinBase() {
    private val remoteDataSource: CharactersRemoteDataSource by KoinJavaComponent.inject(
        CharactersRemoteDataSource::class.java
    )

    @Test
    fun shouldReturnNonEmptyCharactersListResult(): Unit = runBlocking {
        val resultData = remoteDataSource.getNextCharacterPage()

        assertThat(resultData, isA(Result.Success(listOf<Character>()).javaClass))
        assertThat((resultData as Result.Success).data.isNotEmpty(), `is`(true))
    }

    @Test
    fun shouldReturnExpectCharactersData(): Unit = runBlocking {
        characterWithCompleteData.let {
            val resultData = remoteDataSource.getCharacterById(it.id)
            assertThat(resultData, isA(Result.Success(listOf<Character>()).javaClass))

            val returnedCharacter = (resultData as Result.Success).data
            assertThat(returnedCharacter, equalTo(it))
        }
    }
}