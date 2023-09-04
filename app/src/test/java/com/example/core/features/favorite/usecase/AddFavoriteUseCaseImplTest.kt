package com.example.core.features.favorite.usecase

import com.example.core.features.favorites.data.repository.FavoritesRepository
import com.example.core.features.favorites.usecase.AddFavoriteUseCase
import com.example.core.features.favorites.usecase.AddFavoriteUseCaseImpl
import com.example.core.utils.ResultStatus
import com.example.marvel_app.utils.MainCoroutineRule
import com.example.marvel_app.utils.factory.CharacterFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddFavoriteUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: FavoritesRepository

    private lateinit var addFavoriteUseCase: AddFavoriteUseCaseImpl

    private val character = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)

    @Before
    fun setup() {
        addFavoriteUseCase = AddFavoriteUseCaseImpl(
            repository,
            mainCoroutineRule.testDispatcherProvider
        )
    }

    @Test
    fun `should return Success from ResultStatus when add favorite`() =
        runTest {
            //Arrange
            whenever(repository.saveFavorite(character))
                .thenReturn(Unit)

            //Act
            val result = addFavoriteUseCase.invoke(AddFavoriteUseCase
                .ParamsAddFavorite(character.id, character.name, character.imageUrl))

            // Assert
            val resultList = result.toList()
            Assert.assertEquals(ResultStatus.Loading, resultList[0])
            Assert.assertTrue(resultList[1] is ResultStatus.Success)
        }

    @Test
    fun `should return Error from ResultStatus when add favorite`() =
        runTest {
            //Arrange
            whenever(repository.saveFavorite(character)).thenAnswer{
                throw Throwable()
            }

            //Act
            val result = addFavoriteUseCase.invoke(AddFavoriteUseCase.ParamsAddFavorite(
                character.id, character.name, character.imageUrl))

            // Assert
            val resultList = result.toList()
            Assert.assertEquals(ResultStatus.Loading, resultList[0])
            Assert.assertTrue(resultList[1] is ResultStatus.Error)
    }

}