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

    private val _headerList = mutableStateListOf<Characters>()
    val headerList: List<Characters> = _headerList

    private val _bodyList = mutableStateListOf<Characters>()
    val bodyList: List<Characters> = _bodyList

    private val _isNetworkAvailable = mutableStateOf(true)
    val isNetworkAvailable = _isNetworkAvailable

    fun getAllCharacters(){
        viewModelScope.launch {
            _charactersList.clear()
            val (listResult, isNetworkAvailable) = charactersRepository.getCharacters()
            _charactersList.addAll(listResult)
            _isNetworkAvailable.value = isNetworkAvailable
            updateLists()
        }
    }

    private fun updateLists(){
        _headerList.clear()
        _bodyList.clear()
        _charactersList.forEach {
            for (index in _charactersList.indices){
                if(index < 5 && _headerList.size < 5){
                    _headerList.add(_charactersList[index])
                }
                if(index > 4 && _bodyList.size < 16){
                    _bodyList.add(_charactersList[index])
                }
            }
        }
    }

    fun getNewCharacters(){
        viewModelScope.launch {
            _bodyList.addAll(charactersRepository.loadNewCharacters())
        }
    }
}