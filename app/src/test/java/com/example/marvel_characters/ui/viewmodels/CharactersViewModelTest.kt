package com.example.marvel_characters.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.example.marvel_characters.Result
import com.example.marvel_characters.Samples.charactersWithNonRepeatedElements
import com.example.marvel_characters.TestWithKoinBase
import com.example.marvel_characters.repository.Repository
import com.example.marvel_characters.ui.compose.START_ON_OFFILINE_MODE_ARG_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class CharactersViewModelTest : TestWithKoinBase() {
    private lateinit var charactersViewModel: CharactersViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    override fun setup() {
        super.setup()
        MockitoAnnotations.openMocks(this)
        repository = mock(Repository::class.java)
        savedStateHandle = mock(SavedStateHandle::class.java)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    class MainDispatcherRule(
        private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    ) : TestWatcher() {
        override fun starting(description: org.junit.runner.Description) {
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: org.junit.runner.Description) {
            Dispatchers.resetMain()
        }
    }
    @Test
    fun shouldInitializeFetchingCharactersFromNextWebResult() {
        val sampleListResult = Result.Success(charactersWithNonRepeatedElements)

            runBlocking {

                `when`(repository.getNextPage()).thenReturn(sampleListResult)

                `when`(savedStateHandle.get<Boolean>(START_ON_OFFILINE_MODE_ARG_KEY)).thenReturn(false)

                charactersViewModel =  CharactersViewModel(savedStateHandle =savedStateHandle, repository = repository)


                assertThat(charactersViewModel.uiState.value.characters, `is`(charactersWithNonRepeatedElements))
            }
    }

    @Test
    fun shouldReturnCanRequestNewCharactersPageFalseBecauseOnOfflineMode() {
        val sampleListResult = Result.Success(charactersWithNonRepeatedElements)

        runBlocking {

            `when`(repository.getNextPage()).thenReturn(sampleListResult)

            `when`(savedStateHandle.get<Boolean>(START_ON_OFFILINE_MODE_ARG_KEY)).thenReturn(true)

            charactersViewModel =  CharactersViewModel(savedStateHandle =savedStateHandle, repository = repository)

            assertThat(charactersViewModel.uiState.value.canRequestNewCharactersPage, `is`(false))
        }
    }



}