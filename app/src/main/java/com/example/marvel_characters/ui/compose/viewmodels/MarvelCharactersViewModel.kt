package com.example.marvel_characters.ui.compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_characters.BaseDataUiState
import com.example.marvel_characters.Result
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.repository.Repository
import com.example.marvel_characters.succeeded
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MarvelCharactersViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow(MarvelCharactersUIState(loading = true))
    val uiState: StateFlow<MarvelCharactersUIState> = _uiState

    val hasNextPage = repository.hasNextPage()

    init {
        viewModelScope.launch {
            fetchNextPageCharacters()
        }
    }

    private fun fetchNextPageCharacters() {
        viewModelScope.launch {
            val result = repository.getNextPage()
            if (result.succeeded) {
                val updatedCharactersList =
                    uiState.value.marvelCharacters + (result as Result.Success).data
                _uiState.value = MarvelCharactersUIState(
                    marvelCharacters = updatedCharactersList
                )
            } else {
                val foundException = result as Result.Error
                _uiState.value =
                    uiState.value.copy(error = foundException.exception.message, loading = false)
            }
        }
    }

    fun needsToGetCharactersFromNextPage() {
        _uiState.value = uiState.value.copy(loading = true)
        fetchNextPageCharacters()
    }
}

data class MarvelCharactersUIState(
    val marvelCharacters: List<MarvelCharacter> = emptyList(),
    override val loading: Boolean = false,
    override val error: String? = null
) :
    BaseDataUiState(loading, error)

