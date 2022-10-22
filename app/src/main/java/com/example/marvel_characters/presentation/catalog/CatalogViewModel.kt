package com.example.marvel_characters.presentation.catalog

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_characters.domain.models.Characters
import com.example.marvel_characters.domain.repositories.CharactersRepository
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _charactersList = mutableStateListOf<Characters>()
    val charactersList: List<Characters> = _charactersList

    private val _isNetworkAvailable = mutableStateOf(true)
    val isNetworkAvailable = _isNetworkAvailable

    fun getAllCharacters(){
        viewModelScope.launch {
            _charactersList.clear()
            val (listResult, isNetworkAvailable) = charactersRepository.getCharacters()
            _charactersList.addAll(listResult)
            _isNetworkAvailable.value = isNetworkAvailable
        }
    }

}