package com.luisedu.marvel_app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luisedu.marvel_app.data.repository.MarvelApiRepository
import com.luisedu.marvel_app.model.Data
import com.luisedu.marvel_app.model.MarvelApiResponse
import com.luisedu.marvel_app.viewmodel.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        viewModel = HomeViewModel(repository)
    }

    @Test
    fun fetchCharactersList_success() {
        val response: MarvelApiResponse = mockk()
        val repositoryList: Data? = null

        if (repositoryList != null) {
            every { response.data } returns repositoryList
        }

        viewModel.fetchCharactersList()

        Assert.assertEquals(HomeViewModel.Loading.ShowLoading, viewModel.loading.value)
        Assert.assertEquals(repositoryList, viewModel.marvelCharacterResponse.value)
    }

    @Test
    fun fetchCharactersList_error() {
        val error = Exception()

        every { repository.fetchCharactersList(viewModel)} answers { viewModel.onError(error)}

        viewModel.fetchCharactersList()

        Assert.assertEquals(HomeViewModel.Loading.HideLoading, viewModel.loading.value)
        Assert.assertEquals(error, viewModel.errorLiveData.value)
    }
}