package com.example.marvel_characters.ui.compose.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_characters.BaseDataUiState
import com.example.marvel_characters.Result
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.repository.Repository
import com.example.marvel_characters.succeeded
import com.example.marvel_characters.ui.compose.CHARACTER_LIST_ARG_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MarvelCharactersViewModel(
    savedStateHandle: SavedStateHandle, private val repository: Repository
) : ViewModel() {
    private val isOnOfflineMode: Boolean = savedStateHandle.get<Boolean>(CHARACTER_LIST_ARG_KEY)!!

    private val hasNextPage = repository.hasNextPage() && !isOnOfflineMode
    private val _uiState =
        MutableStateFlow(MarvelCharactersUIState(loading = true, hasNextPage = hasNextPage))
    val uiState: StateFlow<MarvelCharactersUIState> = _uiState

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private fun fetchNextPageCharacters() {
        viewModelScope.launch {
            val result = repository.getNextPage()
            if (result.succeeded) {
                updateCharacterList(result)
            } else {
                updateUiWithError(result)
            }
        }
    }

    private fun updateUiWithError(result: Result<List<MarvelCharacter>>) {
        val foundException = result as Result.Error
        _uiState.value =
            uiState.value.copy(error = foundException.exception, loading = false)
    }

    private fun updateCharacterList(result: Result<List<MarvelCharacter>>) {
        val updatedCharactersList =
            uiState.value.marvelCharacters + (result as Result.Success).data
        _uiState.value = MarvelCharactersUIState(
            marvelCharacters = updatedCharactersList, hasNextPage = hasNextPage
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
                _uiState.value = MarvelCharactersUIState(
                    marvelCharacters = marvelCharacters, hasNextPage = hasNextPage
                )
            }
        }
    }
}

data class MarvelCharactersUIState(
    val marvelCharacters: List<MarvelCharacter> = emptyList(),
    override val loading: Boolean = false,
    override val error: Exception? = null,
    val hasNextPage: Boolean
) :
    BaseDataUiState(loading, error) {
    val canRequestNewCharactersPage = hasNextPage && !hadAnError()
}

