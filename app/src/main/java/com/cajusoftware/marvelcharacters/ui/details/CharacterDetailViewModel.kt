package com.cajusoftware.marvelcharacters.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cajusoftware.marvelcharacters.data.domain.Character
import com.cajusoftware.marvelcharacters.data.repositories.CharacterRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    val character: LiveData<Character?>
        get() = characterRepository.character.asLiveData()

    fun fetchCharacter(characterId: Int) {
        viewModelScope.launch {
            characterRepository.fetchCharacter(characterId)
        }
    }
}