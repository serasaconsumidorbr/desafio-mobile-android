package com.example.marvelapp.features.characterslist.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.preferences.protobuf.ExperimentalApi
import androidx.paging.PagingData
import com.example.marvelapp.features.characterslist.data.dto.CharacterDetails
import com.example.marvelapp.features.characterslist.data.dto.Comics
import com.example.marvelapp.features.characterslist.data.dto.Series
import com.example.marvelapp.features.characterslist.data.dto.Thumbnail
import com.example.marvelapp.features.characterslist.data.repository.HomeRepository
import com.example.marvelapp.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var homeRepository: HomeRepository

    private lateinit var  homeViewModel: HomeViewModel

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(homeRepository)
    }

    @Test
    fun `should validate the paging data object values when calling charactersPagingData`() =
        runTest {
            //Arrange
            whenever(
                homeRepository.getCharacters(any())
            ).thenReturn(
                flowOf(
                    PagingData.from(listOf(CharacterDetails("","", Comics(""), Series(""),"",
                        Thumbnail("","")
                    )))
                )
            )

            //Act
            val result = homeViewModel.getCharacters("")

            //Assert
            assertNotNull(result.first())
        }

    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling returns an exception`() =
        runTest {
            whenever(homeRepository.getCharacters(any()))
                .thenThrow(RuntimeException())

            homeViewModel.getCharacters("")
        }
}