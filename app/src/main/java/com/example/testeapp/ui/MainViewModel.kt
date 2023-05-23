package com.example.testeapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeapp.domain.CharactersManager
import com.example.testeapp.model.MarvelCharacter
import com.example.testeapp.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val charactersManager: CharactersManager) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
    var offset = 0

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            charactersManager.getChracters(offset).collect { result ->
                when(result.status){
                    Result.Status.SUCCESS -> {
                        _state.update {
                            it.copy(characterList = result.data as List<MarvelCharacter>, inProgress = false)
                        }
                    }
                    Result.Status.IN_PROGRESS -> {
                        _state.update {
                            it.copy(inProgress = true)
                        }
                    }
                    Result.Status.ERROR -> {
                        _state.update {
                            it.copy(inProgress = false, errorMessage = result.error?.status_message ?: result.message)
                        }
                    }
                }
            }
        }
    }

    fun update(character: MarvelCharacter) {
        viewModelScope.launch {
            charactersManager.updateChracter(character)
        }
    }

    fun isLoading() {
        _state.update {
            it.copy(inProgress = true)
        }
    }
}



data class UiState(
    val characterList: List<MarvelCharacter>? = null,
    val inProgress: Boolean? = null,
    val errorMessage: String? = null
){
    val topComicAppearance = if (characterList.isNullOrEmpty()) {
        emptyList()
    } else {
        characterList.sortedByDescending { character -> character.comics.items.size }
            .subList(0, minOf(characterList.size, 5))
    }
}