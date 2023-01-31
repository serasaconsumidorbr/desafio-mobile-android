package welias.marvel.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import welias.marvel.core.constants.STEP_OFFSET
import welias.marvel.core.extensions.runTestViewModel
import welias.marvel.core.utils.Utils
import welias.marvel.domain.usecase.GetCharactersUseCase
import welias.marvel.presentation.mapper.toCharacterUi
import welias.marvel.presentation.ui.fragments.home.DataApi
import welias.marvel.presentation.ui.fragments.home.HomeViewModel
import welias.marvel.presentation.ui.fragments.home.HomeState
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private val useCase: GetCharactersUseCase = mockk(relaxed = true)

    private fun setupViewModel() = HomeViewModel(useCase = useCase)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getLisCharacters Should call useCase execute When is called`() =
        runTestViewModel(testDispatcher) {
            // Given
            val viewModel = setupViewModel()
            val offset = viewModel.homeState.value.dataApi.nextOffset

            // When
            viewModel.getListCharacters()

            // Then
            verify { useCase.execute(offset) }
        }

    @Test
    fun `uiState Should return a correct list state When use case execute return a list characters domain`() =
        runTestViewModel(testDispatcher) {
            // Given
            val charactersDomain = Utils.getCharactersDomain()
            val charactersDomainFlow = flowOf(charactersDomain)
            val charactersUI = charactersDomain.map { it.toCharacterUi() }

            val viewModel = setupViewModel()
            val offset = viewModel.homeState.value.dataApi.nextOffset
            val homeStateInitial = HomeState()
            val uiStateLoading = homeStateInitial.copy(isLoading = true)
            val uiStateLoaded =
                uiStateLoading.copy(
                    characters = emptyList(),
                    listTopCharacters = charactersUI,
                    isLoading = false,
                    error = null,
                    isFirstRequisition = false,
                    dataApi = DataApi(homeStateInitial.dataApi.nextOffset.plus(STEP_OFFSET))
                )
            val listUiStates = listOf(
                homeStateInitial,
                uiStateLoaded
            )
            val flowScope = CoroutineScope(testDispatcher)
            val statesFlow = viewModel.homeState.shareIn(
                scope = flowScope,
                replay = listUiStates.size,
                started = SharingStarted.Eagerly
            )

            // When
            coEvery { useCase.execute(offset = offset) } returns charactersDomainFlow
            viewModel.getListCharacters()

            // Then
            assertEquals(statesFlow.replayCache, listUiStates)
            flowScope.cancel()
        }
}
