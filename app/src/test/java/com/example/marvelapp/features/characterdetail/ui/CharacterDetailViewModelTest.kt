import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.preferences.protobuf.ExperimentalApi
import androidx.lifecycle.Observer
import com.example.marvelapp.features.characterdetail.data.model.CharacterDetailModel
import com.example.marvelapp.features.characterdetail.data.repository.CharacterDetailRepository
import com.example.marvelapp.features.characterdetail.ui.CharacterDetailViewModel
import com.example.marvelapp.features.characterdetail.ui.UiState
import com.example.marvelapp.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalApi
@RunWith(MockitoJUnitRunner::class)
class CharacterDetailViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var uiStateObserver: Observer<UiState>

    @Mock
    lateinit var characterDetailRepository: CharacterDetailRepository

    private lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun setup() {
        viewModel = CharacterDetailViewModel(characterDetailRepository)
        viewModel.uiState.observeForever(uiStateObserver)
    }

    @Test
    fun `should notify uiState with success when get character returns non null object`() =
        runTest {
            //Arrange
            val characterDetailModel = CharacterDetailModel("0", "", "", "")
            whenever(
                characterDetailRepository.getCharacterDetail(any())
            ).thenReturn(
                characterDetailModel
            )

            //Act
            viewModel.getCharacterDetail(0)

            //Assert
            verify(uiStateObserver).onChanged(isA<UiState.Success>())

            val uiStateSuccess = viewModel.uiState.value as UiState.Success
            val model = uiStateSuccess.data

            assertEquals("0", model.id)
        }

    @Test
    fun `should notify uiState with Error when get character returns an exception`() = runTest {
        // Arrange
        whenever(characterDetailRepository.getCharacterDetail(any()))
            .thenReturn(
                null
            )

        // Act
        viewModel.getCharacterDetail(0)

        //Assert
        verify(uiStateObserver).onChanged(isA<UiState.Error>())
    }
}