package br.com.marvel.presentation.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.marvel.domain.models.Character
import br.com.marvel.domain.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    characterRepository: CharacterRepository
) : ViewModel() {

    val characters: Flow<PagingData<Character>> =
        characterRepository.getCharacters().cachedIn(viewModelScope)
}