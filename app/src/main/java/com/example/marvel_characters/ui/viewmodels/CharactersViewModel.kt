package com.example.marvel_characters.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_characters.BaseDataUiState
import com.example.marvel_characters.Result
import com.example.marvel_characters.domain.Character
import com.example.marvel_characters.network.isInternetAvailable
import com.example.marvel_characters.repository.Repository
import com.example.marvel_characters.succeeded
import com.example.marvel_characters.ui.compose.START_ON_OFFILINE_MODE_ARG_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersViewModel(
    savedStateHandle: SavedStateHandle, private val repository: Repository
) : ViewModel() {
    private val isOnOfflineMode: Boolean = savedStateHandle.get<Boolean>(START_ON_OFFILINE_MODE_ARG_KEY)!!

    private val _uiState =
        MutableStateFlow(CharactersUIState(loading = true, hasNextPage = hasNextPage()))
    val uiState: StateFlow<CharactersUIState> = _uiState

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private fun fetchNextPageCharacters() {
        viewModelScope.launch {
            val result = repository.getNextPage()
            if (result.succeeded) {
                val charactersList = (result as Result.Success).data
                updateCharacterList(charactersList)

                updateSavedLocalCharacters(charactersList)
            } else {
                updateUiWithError(result)
            }
        }
    }

    private suspend fun updateSavedLocalCharacters(charactersList: List<Character>) {
        charactersList.forEach {
            repository.updateCharacter(it)
        }
    }

    private fun updateUiWithError(result: Result<List<Character>>) {
        val foundException = result as Result.Error
        _uiState.value =
            uiState.value.copy(error = foundException.exception, loading = false)
    }

    private fun updateCharacterList(newCharactersList: List<Character>) {
        val updatedCharactersList =
            uiState.value.characters + newCharactersList
        _uiState.value = CharactersUIState(
            characters = updatedCharactersList, hasNextPage = hasNextPage()
        )
    }

    fun fetchCharactersFromNextWebResult() {
        _uiState.value = uiState.value.copy(loading = true)
        fetchNextPageCharacters()
    }

    private suspend fun fetchData() {
        if (isOnOfflineMode) {
            getLocalCharacters()
        } else {
            fetchCharactersFromNextWebResult()
        }
    }

    private suspend fun getLocalCharacters() {
        _uiState.value = uiState.value.copy(loading = true)

        repository.observeSavedCharactersList().collectLatest {

            if (it.succeeded) {
                val marvelCharacters = (it as Result.Success).data
                _uiState.value = CharactersUIState(
                    characters = marvelCharacters, hasNextPage = hasNextPage()
                )
            } else {
                val foundException = (it as Result.Error).exception
                _uiState.value = uiState.value.copy(error = foundException)
            }
        }
    }

    private fun hasNextPage() = repository.hasNextPage() && !isOnOfflineMode
}

data class CharactersUIState(
    val characters: List<Character> = emptyList(),
    override val loading: Boolean = false,
    override val error: Exception? = null,
    val hasNextPage: Boolean
) :
    BaseDataUiState(loading, error) {
    val canRequestNewCharactersPage = hasNextPage && !hadAnError() && !loading
}

