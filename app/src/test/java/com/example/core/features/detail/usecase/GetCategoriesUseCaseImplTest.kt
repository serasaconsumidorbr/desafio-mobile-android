package com.example.core.features.detail.usecase

import com.example.core.features.details.data.repository.CategoriesRepository
import com.example.core.features.details.usecase.GetCategoriesUseCase
import com.example.core.features.details.usecase.GetCategoriesUseCaseImpl
import com.example.core.utils.ResultStatus
import com.example.marvel_app.utils.MainCoroutineRule
import com.example.marvel_app.utils.factory.CharacterFactory
import com.example.marvel_app.utils.factory.ComicFactory
import com.example.marvel_app.utils.factory.EventFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCategoriesUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: CategoriesRepository

    private lateinit var getCategoriesUseCaseImpl: GetCategoriesUseCaseImpl

    private val character = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)

    private val comics = listOf(ComicFactory().create(ComicFactory.FakeComic.FakeComic1))

    private val events = listOf(EventFactory().create(EventFactory.FakeEvent.FakeEvent1))

    @Before
    fun setup() {
        getCategoriesUseCaseImpl = GetCategoriesUseCaseImpl(
            repository,
            mainCoroutineRule.testDispatcherProvider
        )
    }

    @Test
    fun `should return Success from ResultStatus when get both requests return success`() =
        runTest {
            // Arrange
            whenever(repository.getComics(character.id)).thenReturn(comics)
            whenever(repository.getEvent(character.id)).thenReturn(events)

            // Act
            val result = getCategoriesUseCaseImpl
                .invoke(GetCategoriesUseCase.GetCategoriesParams(character.id))

            // Assert
            val resultList = result.toList()
            assertEquals(ResultStatus.Loading, resultList[0])
            assertTrue(resultList[1] is ResultStatus.Success)
        }

    @Test
    fun `should return Error from ResultStatus when get events request returns error`() =
        runTest {
            // Arrange
            whenever(repository.getComics(character.id)).thenAnswer {
                throw Throwable()
            }

            // Act
            val result = getCategoriesUseCaseImpl
                .invoke(GetCategoriesUseCase.GetCategoriesParams(character.id))

            // Assert
            val resultList = result.toList()
            assertEquals(ResultStatus.Loading, resultList[0])
            assertTrue(resultList[1] is ResultStatus.Error)
        }

    @Test
    fun `should return Error from ResultStatus when get comics request returns error`() =
        runTest {
            // Arrange
            whenever(repository.getComics(character.id)).thenReturn(comics)
            whenever(repository.getEvent(character.id)).thenAnswer {
                throw Throwable()
            }

            // Act
            val result = getCategoriesUseCaseImpl
                .invoke(GetCategoriesUseCase.GetCategoriesParams(character.id))

            // Assert
            val resultList = result.toList()
            assertEquals(ResultStatus.Loading, resultList[0])
            assertTrue(resultList[1] is ResultStatus.Error)
        }
}