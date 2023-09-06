package com.example.marvel_characters.ui.compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_characters.domain.MarvelCharacter
import com.example.marvel_characters.repository.Repository
import com.example.marvel_characters.succeeded
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import  com.example.marvel_characters.Result
class MarvelCharactersViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow(MarvelCharactersUIState(loading = true))
    val uiState: StateFlow<MarvelCharactersUIState> = _uiState


    init {
        viewModelScope.launch {
            updateScreenWithDefaultSearch()
        }
    }

    private suspend fun updateScreenWithDefaultSearch() {

        val result = repository.getCharactersFromWeb()
        if (result.succeeded) {
            _uiState.value = MarvelCharactersUIState(
                marvelCharacters = (result as Result.Success).data
            )
        } else {
            _uiState.value = MarvelCharactersUIState(
                error = (result as Result.Error).exception.message
            )
        }
    }
}


data class MarvelCharactersUIState(
    val marvelCharacters: List<MarvelCharacter> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
