package com.example.feature.hero_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.heroes.model.Hero
import com.example.domain.heroes.usecase.LoadCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(private val loadCharactersUseCase: LoadCharactersUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<HeroViewState>(HeroViewState.Loading)
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<HeroViewState> = _uiState


    init {
        viewModelScope.launch {
            loadCharactersUseCase.invoke(0).catch {  }.collect {
                _uiState.value = HeroViewState.Success(it)
            }
        }
    }


}