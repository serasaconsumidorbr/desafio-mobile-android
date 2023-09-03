package com.example.core.features.characters.usecase

import androidx.datastore.preferences.protobuf.ExperimentalApi
import androidx.paging.PagingConfig
import com.example.core.features.characters.data.repository.CharactersRepository
import com.example.marvel_app.utils.MainCoroutineRule
import com.example.marvel_app.utils.factory.CharacterFactory
import com.example.marvel_app.utils.factory.PagingSourceFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
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

    private val fakePagingSource = PagingSourceFactory().create(listOf(hero))

    @Mock
    lateinit var repository: CharactersRepository

    @Before
    fun setup() {
        getCharactersUseCase = GetCharactersUseCaseImpl(repository)
    }

    @Test
    fun `should validate the paging creation when invoke from use case is called`() =
        runTest {
            //Arrange
            whenever(repository.getCharacters(""))
                .thenReturn(fakePagingSource)

            //Act
            val result = getCharactersUseCase.invoke(
                GetCharactersUseCase.GetCharactersParams("", PagingConfig(20))
            )

            //Assert
            verify(repository).getCharacters("")
            assertNotNull(result.first())
        }
}