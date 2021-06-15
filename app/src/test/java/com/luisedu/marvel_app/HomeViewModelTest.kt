package com.luisedu.marvel_app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luisedu.marvel_app.data.repository.MarvelApiRepository
import com.luisedu.marvel_app.model.Data
import com.luisedu.marvel_app.model.MarvelApiResponse
import com.luisedu.marvel_app.viewmodel.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: MarvelApiRepository

    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)

        viewModel = HomeViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchCharactersList_success() = runBlockingTest {
        val response: MarvelApiResponse = mockk()
        val repositoryList: Data? = null

        if (repositoryList != null) {
            coEvery { response.data } returns repositoryList
        }

        viewModel.fetchCharactersList()

        Assert.assertEquals(HomeViewModel.Loading.ShowLoading, viewModel.loading.value)
        Assert.assertEquals(repositoryList, viewModel.marvelCharacterResponse.value)
    }
}