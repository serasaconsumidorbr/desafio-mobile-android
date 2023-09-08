package com.example.marvel_characters.ui.compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        viewModelScope.launch {
            addNextPageCharactersResultToScreen()
        }
    }

    private fun addNextPageCharactersResultToScreen() {
        _uiState.value = uiState.value.copy(loading = true)
        viewModelScope.launch {
            val result = repository.getNextPage()
            if (result.succeeded) {
                val updatedCharactersList =
                    uiState.value.marvelCharacters + (result as Result.Success).data
                _uiState.value = MarvelCharactersUIState(
                    marvelCharacters = updatedCharactersList, couldGetMoreFromWebService = repository.couldGetMoreFromWebService()
                )
            } else {
                _uiState.value = MarvelCharactersUIState(
                    error = (result as Result.Error).exception.message
                )
            }
        }
    }

    fun listBottomIsVisible() {
        addNextPageCharactersResultToScreen()
    }
}


data class MarvelCharactersUIState(
    val marvelCharacters: List<MarvelCharacter> = emptyList(),
    val couldGetMoreFromWebService: Boolean = true,
    val loading: Boolean = false,
    val error: String? = null
) {
}
