package com.cajusoftware.marvelcharacters.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cajusoftware.marvelcharacters.data.domain.Character
import com.cajusoftware.marvelcharacters.data.repositories.CharacterRepository
import com.cajusoftware.tests.fakes.FakeData.character
import com.cajusoftware.tests.rules.MainDispatcherRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class CharacterDetailViewModelTest {

    private lateinit var viewModel: CharacterDetailViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: CharacterRepository = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = StandardTestDispatcher()

    private val observer = mockk<Observer<Character?>>()

    private val slot = slot<Character>()

    private val characterList = arrayListOf<Character>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() = runTest {

        MockKAnnotations.init(this, relaxUnitFun = true)

        coEvery { repository.character } coAnswers { flow { emit(character) } }

        viewModel = CharacterDetailViewModel(repository)

        dispatcher.run {
            viewModel.character.observeForever(observer)
        }

        coEvery { observer.onChanged(capture(slot)) } coAnswers {
            characterList.add(slot.captured)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun characterDetailViewModel_character_verifyCharacter() {
        dispatcher.run {
            viewModel.fetchCharacter(0)
            assertEquals(character, characterList.first())
        }
    }
}