package com.example.marvel_characters.presentation.catalog

import com.example.marvel_characters.domain.repositories.CharactersRepositoryImpl
import com.example.marvel_characters.mock.AppMocks.charactersListMock
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatalogViewModelTest{

    @get:Rule
    val mockkRule = MockKRule(this)

    private val charactersRepository = mockk<CharactersRepositoryImpl>()
    private lateinit var viewModel: CatalogViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = spyk(CatalogViewModel(charactersRepository))
    }

    @Test
    fun getCharacters_headerList_success() = runBlocking {
        coEvery {
            charactersRepository.getCharacters()
        } returns Pair(charactersListMock, true)

        viewModel.getAllCharacters()

        assert(viewModel.headerList[0].id == 100L)
        assert(viewModel.headerList[1].id == 101L)
        assert(viewModel.headerList[2].id == 102L)
        assert(viewModel.headerList[3].id == 103L)
        assert(viewModel.headerList[4].id == 104L)
    }

    @Test
    fun getCharacters_bodyList_success() = runBlocking {
        coEvery {
            charactersRepository.getCharacters()
        } returns Pair(charactersListMock, true)

        viewModel.getAllCharacters()

        assert(viewModel.bodyList[0].id == 105L)
        assert(viewModel.bodyList[1].id == 106L)
        assert(viewModel.bodyList[2].id == 107L)
        assert(viewModel.bodyList[3].id == 108L)
        assert(viewModel.bodyList[4].id == 109L)
    }

    @Test
    fun getCharacters_networkAvailable_sucess() = runBlocking {
        coEvery {
            charactersRepository.getCharacters()
        } returns Pair(charactersListMock, true)

        viewModel.getAllCharacters()

        assert(viewModel.isNetworkAvailable.value)
    }

    @Test
    fun getCharacters_networkAvailable_failure() = runBlocking {
        coEvery {
            charactersRepository.getCharacters()
        } returns Pair(charactersListMock, false)

        viewModel.getAllCharacters()

        assert(!viewModel.isNetworkAvailable.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun finish(){
        Dispatchers.resetMain()
    }

}