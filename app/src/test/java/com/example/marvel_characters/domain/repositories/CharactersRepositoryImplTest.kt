package com.example.marvel_characters.domain.repositories

import com.example.marvel_characters.data.local.CharactersDao
import com.example.marvel_characters.data.remote.MarvelAPI
import com.example.marvel_characters.domain.models.Characters
import com.example.marvel_characters.mock.AppMocks.apiResponseMock
import com.example.marvel_characters.mock.AppMocks.charactersListMock
import com.example.marvel_characters.mock.AppMocks.getCharactersEntityListMock
import com.example.marvel_characters.service.CheckNetworkConnection
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersRepositoryImplTest{

    @get:Rule
    val mockkRule = MockKRule(this)

    private val api = mockk<MarvelAPI>()
    private val db = mockk<CharactersDao>()
    private val connection = mockk<CheckNetworkConnection>()
    private val reposioty = CharactersRepositoryImpl(api, db, connection)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
        coEvery {
            api.getCharacters(any(), any(), any())
        } returns apiResponseMock
        coEvery {
            db.insert(any())
        }
        coEvery {
            db.deleteCharacters()
        } returns Unit
    }

    @Test
    fun getCharacters_listCharacters_success() = runBlocking {
        coEvery {
            connection.isNetworkAvailable()
        } returns true
        coEvery {
            db.getCharacters()
        } returns getCharactersEntityListMock()

        assert(reposioty.getCharacters() == Pair(charactersListMock, true))
    }

    @Test
    fun getCharacters_listCharacters_empty() = runBlocking {
        coEvery {
            connection.isNetworkAvailable()
        } returns true
        coEvery {
            db.getCharacters()
        } returns listOf()
        val emptyList = listOf<Characters>()

        assert(reposioty.getCharacters() == Pair(emptyList, true))
    }

    @Test
    fun getCharacters_networkAvailable_success() = runBlocking {
        coEvery {
            connection.isNetworkAvailable()
        } returns true
        coEvery {
            db.getCharacters()
        } returns listOf()
        val emptyList = listOf<Characters>()

        assert(reposioty.getCharacters() == Pair(emptyList, true))
    }

    @Test
    fun getCharacters_networkAvailable_failure() = runBlocking {
        coEvery {
            connection.isNetworkAvailable()
        } returns false
        coEvery {
            db.getCharacters()
        } returns listOf()
        val emptyList = listOf<Characters>()

        assert(reposioty.getCharacters() == Pair(emptyList, false))
    }
}