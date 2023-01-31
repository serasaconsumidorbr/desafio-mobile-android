package welias.marvel.presentation.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import welias.marvel.core.constants.FOUR
import welias.marvel.core.constants.STEP_OFFSET
import welias.marvel.core.exception.ErrorException
import welias.marvel.core.exception.ErrorException.NoConnectionError
import welias.marvel.core.exception.ErrorException.ServerError
import welias.marvel.domain.usecase.GetCharactersUseCase
import welias.marvel.presentation.mapper.toCharacterUi
import welias.marvel.presentation.model.CharacterUI

class HomeViewModel(
    private val useCase: GetCharactersUseCase
) : ViewModel() {

    private val characters = mutableListOf<CharacterUI>()
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> get() = _homeState

    fun getListCharacters() {
        viewModelScope.launch {
            val offset = _homeState.value.dataApi.nextOffset

            useCase.execute(offset)
                .onStart { handleLoading(true) }
                .onCompletion { handleLoading(false) }
                .map { characters ->
                    characters.map { it.toCharacterUi() }
                }.catch {
                    it.showConnectionErrorOrThrowable()
                }.collect(::handleUI)
        }
    }

    private fun handleLoading(value: Boolean) {
        _homeState.update { it.copy(isLoading = value) }
    }

    private fun handleUI(charactersUI: List<CharacterUI>) {
        val topCharacterUI = mutableListOf<CharacterUI>()
        val mainCharacterUI = mutableListOf<CharacterUI>()

        sumList(charactersUI).forEachIndexed { index, characterUI ->
            if (index <= FOUR) topCharacterUI.add(characterUI)
            else mainCharacterUI.add(characterUI)
        }

        _homeState.update { state ->
            state.copy(
                dataApi = DataApi(nextOffset = state.dataApi.nextOffset.plus(STEP_OFFSET)),
                characters = mainCharacterUI.toList(),
                listTopCharacters = topCharacterUI.toList(),
                error = null,
                isFirstRequisition = false
            )
        }
    }

    private fun Throwable.showConnectionErrorOrThrowable() {
        handleError(
            if (this is NoConnectionError) {
                NoConnectionError
            } else {
                ServerError
            }
        )
    }

    private fun handleError(error: ErrorException) {
        _homeState.update {
            it.copy(error = error)
        }
    }

    private fun sumList(charactersUI: List<CharacterUI>): List<CharacterUI> {
        characters.addAll(charactersUI)
        return characters.toList()
    }
}
