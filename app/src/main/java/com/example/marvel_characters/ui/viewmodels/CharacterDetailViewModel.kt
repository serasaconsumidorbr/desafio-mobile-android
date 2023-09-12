package com.example.marvel_characters.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_characters.BaseDataUiState
import com.example.marvel_characters.Result
import com.example.marvel_characters.domain.Character
import com.example.marvel_characters.repository.Repository
import com.example.marvel_characters.succeeded
import com.example.marvel_characters.ui.compose.CHARACTER_ID_ARG_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CharacterDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MarvelCharacterUIState(loading = true))
    val uiState: StateFlow<MarvelCharacterUIState> = _uiState
    private val characterId: String = savedStateHandle.get<String>(CHARACTER_ID_ARG_KEY)!!

    init {
        viewModelScope.launch {
            fetchCharacter()
        }
    }

    private suspend fun fetchCharacter() {
        val result = repository.getSavedCharacter(characterId)
        if (result.succeeded) {
            val marvelCharacter = (result as Result.Success).data
            _uiState.value = MarvelCharacterUIState(marvelCharacter, isCharacterSaved = true)

        } else {
            fetchCharacterFromWeb()
        }
    }



    private suspend fun fetchCharacterFromWeb() {

        val result = repository.getCharacterByIdFromWeb(id = characterId)
        if (result.succeeded) {
            val marvelCharacter = (result as Result.Success).data
            _uiState.value = MarvelCharacterUIState(marvelCharacter, isCharacterSaved = false)

        } else {
            _uiState.value = MarvelCharacterUIState(
                error = (result as Result.Error).exception
            )
        }

    }

    fun onFavoritePressed() {
        viewModelScope.launch {
            if (!uiState.value.isCharacterSaved) {
                saveCharacter()
            } else {
                removeCharacter()
            }
        }
    }

    private suspend fun saveCharacter() {
        repository.saveCharacter(uiState.value.character!!)
        _uiState.value = uiState.value.copy(isCharacterSaved = true)
    }


    private suspend fun removeCharacter() {
        repository.deleteCharacter(uiState.value.character!!)
        _uiState.value = uiState.value.copy(isCharacterSaved = false)
    }


    data class MarvelCharacterUIState(
        val character: Character? = null,
        override val loading: Boolean = false,
        override val error: Exception? = null,
        val isCharacterSaved: Boolean = false
    ) :
        BaseDataUiState(loading, error)
}