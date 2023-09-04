package com.example.core.features.characters.usecase

import androidx.datastore.preferences.protobuf.ExperimentalApi
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.features.characters.data.repository.CharactersRepository
import com.example.core.features.characters.domain.model.Character
import com.example.marvel_app.utils.MainCoroutineRule
import com.example.marvel_app.utils.factory.CharacterFactory
import com.example.marvel_app.utils.factory.PagingSourceFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
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
class GetCharactersUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var getCharactersUseCase: GetCharactersUseCase

    private val hero = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)

    private val fakePagingData = PagingSourceFactory().createPagingData(listOf(hero))

    @Mock
    lateinit var repository: CharactersRepository

    @Before
    fun setup() {
        getCharactersUseCase = GetCharactersUseCaseImpl(repository)
    }

    @Test
    fun `should validate the paging creation when invoke from use case is called`() =
        runTest {
            // Arrange
            whenever(repository.getCachedCharacters(any(), any()))
                .thenReturn(fakePagingData)

            // Act
            val result = getCharactersUseCase.invoke(
                GetCharactersUseCase.GetCharactersParams("", PagingConfig(20))
            )

            // Assert
            assertEquals(fakePagingData, result)
        }
}