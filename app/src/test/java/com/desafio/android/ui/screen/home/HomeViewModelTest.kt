package com.desafio.android.ui.screen.home

import com.desafio.android.MainCoroutineRule
import com.desafio.android.core.base.execution.CaseResult
import com.desafio.android.domain.cases.home.GetMarvelCharactersCase
import com.desafio.android.domain.entity.MarvelCharacter
import com.desafio.android.factory.MarvelCharacterFactory
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
internal class HomeViewModelTest {
    private val getMarvelCharactersCase = mock<GetMarvelCharactersCase>()
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val coroutineScope = MainCoroutineRule()

    @Before
    fun create() {
        viewModel = HomeViewModel(
            getMarvelCharactersCase = getMarvelCharactersCase
        )
    }

    @Test
    fun `with initial screen state, getting a filled list of characters successfully`() = runTest {
        whenever(getMarvelCharactersCase(
            offset = 0
        )).thenReturn(getSuccessfulResult())

        val state = viewModel.state

        assertThat(state.characters).isEmpty()

        viewModel.interact(HomeInteraction.GetCharacters)

        assertThat(state.characters).isNotEmpty()
    }

    @Test
    fun `with initial screen state, failing to get a filled list of characters`() = runTest {
        whenever(getMarvelCharactersCase(
            offset = 0
        )).thenReturn(getFailedResult())

        val state = viewModel.state

        assertThat(state.characters).isEmpty()

        viewModel.interact(HomeInteraction.GetCharacters)

        assertThat(state.characters).isEmpty()
    }

    @Test
    fun `with initial screen state, clearing a filled list of characters`() = runTest {
        whenever(getMarvelCharactersCase(
            offset = 0
        )).thenReturn(getSuccessfulResult())

        val state = viewModel.state

        assertThat(state.characters).isEmpty()

        viewModel.interact(HomeInteraction.GetCharacters)

        assertThat(state.characters).isNotEmpty()

        viewModel.interact(HomeInteraction.ResetCharacters)

        assertThat(state.characters).isEmpty()
    }

    @Test
    fun `with initial screen state, setting the last item in the list as visible`() = runTest {
        val state = viewModel.state

        assertThat(state.isLastItemVisible).isFalse()

        viewModel.interact(HomeInteraction.SetIsLastItemVisible(
            isVisible = true
        ))

        assertThat(state.isLastItemVisible).isTrue()
    }

    private fun getSuccessfulResult(): CaseResult<List<MarvelCharacter>> {
        return CaseResult.Success(MarvelCharacterFactory.getCharacters(10))
    }

    private fun getFailedResult(): CaseResult<List<MarvelCharacter>> {
        return CaseResult.Failure(Exception("This is a failure."))
    }

    private fun getEmptyResult(): CaseResult<List<MarvelCharacter>> {
        return CaseResult.Success(emptyList())
    }
}