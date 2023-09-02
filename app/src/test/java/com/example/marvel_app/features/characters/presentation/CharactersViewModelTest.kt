package com.example.marvel_app.features.characters.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.preferences.protobuf.ExperimentalApi
import androidx.paging.PagingData
import com.example.core.features.characters.usecase.GetCharactersUseCase
import com.example.marvel_app.utils.MainCoroutineRule
import com.example.marvel_app.utils.factory.CharacterFactory
import com.nhaarman.mockitokotlin2.any
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
import java.lang.RuntimeException

@ExperimentalApi
@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getCharactersUseCase: GetCharactersUseCase

    private lateinit var  charactersViewModel: CharactersViewModel

    private val charactersFactory = CharacterFactory()


    private val pagingDataCharacters = PagingData.from(
        listOf(
            charactersFactory.create(CharacterFactory.Hero.ThreeDMan),
            charactersFactory.create(CharacterFactory.Hero.ABomb)
        )
    )

    @Before
    fun setup() {
        charactersViewModel = CharactersViewModel(getCharactersUseCase)
    }

    @Test
    fun `should validate the paging data object values when calling charactersPagingData`() =
        runTest {
            whenever(
                getCharactersUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    pagingDataCharacters
                )
            )

            val result = charactersViewModel.charactersPagingData("")

            assertNotNull(result.first())
    }

    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling to the use case returns an exception`() =
        runTest {
            whenever(getCharactersUseCase.invoke(any()))
                .thenThrow(RuntimeException())

            charactersViewModel.charactersPagingData("")
        }
}