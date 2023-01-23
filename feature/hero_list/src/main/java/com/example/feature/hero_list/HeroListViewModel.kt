package com.example.feature.hero_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.heroes.usecase.LoadCharactersUseCase
import com.example.feature.hero_list.state.HeroViewState
import com.example.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val loadCharactersUseCase: LoadCharactersUseCase,
    contextProvider: CoroutineContextProvider
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<HeroViewState>(HeroViewState.Loading)
    val uiState: StateFlow<HeroViewState>
        get() = _uiState


    private val ioContext: CoroutineContext = (contextProvider.io)

    init {
        loadHeroes(0)
    }

    fun loadHeroes(page: Int) {
        _uiState.value = HeroViewState.Loading
        viewModelScope.launch(ioContext) {
            loadCharactersUseCase.invoke(page).catch { }.collect {
                _uiState.value =
                    HeroViewState.Success(it.copy(hero = it.hero))
            }
        }
    }


}