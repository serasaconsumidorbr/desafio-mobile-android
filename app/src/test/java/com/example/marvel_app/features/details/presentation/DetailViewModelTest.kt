package com.example.marvel_app.features.details.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.preferences.protobuf.ExperimentalApi
import androidx.lifecycle.Observer
import com.example.core.features.details.domain.Comic
import com.example.core.features.details.domain.Event
import com.example.core.features.details.usecase.GetCategoriesUseCase
import com.example.core.features.favorites.usecase.AddFavoriteUseCase
import com.example.core.utils.ResultStatus
import com.example.marvel_app.R
import com.example.marvel_app.features.detail.presentation.DetailViewModel
import com.example.marvel_app.features.detail.presentation.DetailViewModel.UiState
import com.example.marvel_app.utils.MainCoroutineRule
import com.example.marvel_app.utils.factory.CharacterFactory
import com.example.marvel_app.utils.factory.ComicFactory
import com.example.marvel_app.utils.factory.EventFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var uiStateObserver: Observer<UiState>

    @Mock
    private lateinit var getCategoryUseCase: GetCategoriesUseCase

    @Mock
    private lateinit var addFavoriteUseCase: AddFavoriteUseCase

    private lateinit var detailViewModel: DetailViewModel

    private val character = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)

    private val comics = listOf(ComicFactory().create(ComicFactory.FakeComic.FakeComic1))

    private val events = listOf(EventFactory().create(EventFactory.FakeEvent.FakeEvent1))

    @Before
    fun setup() {
        detailViewModel = DetailViewModel(getCategoryUseCase, addFavoriteUseCase)
        detailViewModel.uiState.observeForever(uiStateObserver)
    }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns success`() =
        runTest {
            //Arrange
            whenever(getCategoryUseCase.invoke(any())).thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            comics to events
                        )
                    )
                )

            //Act
            detailViewModel.getCategories(character.id)

            //Assert
            verify(uiStateObserver).onChanged(isA<UiState.Success>())

            val uiStateSuccess = detailViewModel.uiState.value as UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            assertEquals(2, categoriesParentList.size)
            assertEquals(
                R.string.details_comics_category, categoriesParentList[0].categoryStringResId
            )
            assertEquals(
                R.string.details_events_category, categoriesParentList[1].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns only comics`() =
        runTest {
            //Arrange
            whenever(getCategoryUseCase.invoke(any())).thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            comics to emptyList()
                        )
                    )
                )
            //Act
            detailViewModel.getCategories(character.id)

            //Assert
            verify(uiStateObserver).onChanged(isA<UiState.Success>())

            val uiStateSuccess = detailViewModel.uiState.value as UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            assertEquals(1, categoriesParentList.size)
            assertEquals(
                R.string.details_comics_category, categoriesParentList[0].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns only events`() =
        runTest {
            //Arrange
            whenever(getCategoryUseCase.invoke(any())).thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            emptyList<Comic>() to events
                        )
                    )
                )
            //Act
            detailViewModel.getCategories(character.id)

            //Assert
            verify(uiStateObserver).onChanged(isA<UiState.Success>())

            val uiStateSuccess = detailViewModel.uiState.value as UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            assertEquals(1, categoriesParentList.size)
            assertEquals(
                R.string.details_events_category, categoriesParentList[0].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Empty from UiState when get character categories returns an empty result list`() =
        runTest {
            //Arrange
            whenever(getCategoryUseCase.invoke(any())).thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            emptyList<Comic>() to emptyList<Event>()
                        )
                    )
                )
            //Act
            detailViewModel.getCategories(character.id)

            //Assert
            verify(uiStateObserver).onChanged(isA<UiState.Empty>())
        }

    @Test
    fun `should notify uiState with Error from UiState when get character categories returns an exception`() =
        runTest {
            // Arrange
            whenever(getCategoryUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Error(Throwable())
                    )
                )

            // Act
            detailViewModel.getCategories(character.id)

            // Assert
            verify(uiStateObserver).onChanged(isA<UiState.Error>())
        }
}