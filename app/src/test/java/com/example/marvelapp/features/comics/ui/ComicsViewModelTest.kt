package com.example.marvelapp.features.comics.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.preferences.protobuf.ExperimentalApi
import androidx.paging.PagingData
import com.example.marvelapp.features.comics.data.model.ComicDetailModel
import com.example.marvelapp.features.comics.data.repository.ComicsRepository
import com.example.marvelapp.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalApi
@RunWith(MockitoJUnitRunner::class)
class ComicsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var comicsRepository: ComicsRepository

    private lateinit var comicsViewModel: ComicsViewModel

    @Before
    fun setup() {
        comicsViewModel = ComicsViewModel(comicsRepository)
    }

    @Test
    fun `should validate the paging data object values when calling charactersPagingData`() =
        runTest {
            //Arrange
            whenever(
                comicsRepository.getComics(any())
            ).thenReturn(
                flowOf(
                    PagingData.from(
                        listOf(
                            ComicDetailModel("", "", "", "")
                        )
                    )
                )
            )

            //Act
            val result = comicsViewModel.getComics(0)

            //Assert
            assertNotNull(result.first())
        }


    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling returns an exception`() =
        runTest {
            whenever(comicsRepository.getComics(any()))
                .thenThrow(RuntimeException())

            comicsViewModel.getComics(0)
        }
}
