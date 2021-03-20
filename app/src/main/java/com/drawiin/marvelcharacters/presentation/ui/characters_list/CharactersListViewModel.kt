package com.drawiin.marvelcharacters.presentation.ui.characters_list

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drawiin.marvelcharacters.domain.interactors.GetCarousel
import com.drawiin.marvelcharacters.domain.interactors.GetCharacters
import com.drawiin.marvelcharacters.domain.model.character.Character
import com.drawiin.marvelcharacters.utils.NAMED_API_KEY
import com.drawiin.marvelcharacters.utils.NAMED_HASH
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Named

class CharactersListViewModel @ViewModelInject constructor(
    private val getCarousel: GetCarousel,
    private val getCharacters: GetCharacters,
    @Named(NAMED_API_KEY) private val apiKey: String,
    @Named(NAMED_HASH) private val hash: String
) : ViewModel() {
    val charactersCarousel get() = _charactersCarousel
    val charactersList get() = _charactersList

    private val _charactersCarousel by lazy { MutableLiveData<List<Character>>() }
    private val _charactersList by lazy { MutableLiveData<List<Character>>() }

    init {
        loadCarousel()
        loadCharactersList()
    }

    private fun loadCarousel() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val heroes = getCarousel.execute(apiKey, hash)
                _charactersCarousel.postValue(heroes)
            } catch (error: RuntimeException) {
                Log.e("Request Error", error.message.toString())
            }
        }
    }

    private fun loadCharactersList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val heroes = getCharacters.execute(apiKey, hash)
                _charactersList.postValue(heroes)
            } catch (error: RuntimeException) {
                Log.e("Request Error", error.message.toString())
            }
        }
    }

}