package dev.ribeiro.bruno.desafioandroid.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dev.ribeiro.bruno.desafioandroid.core.DefaultException
import dev.ribeiro.bruno.desafioandroid.core.LoadingState
import dev.ribeiro.bruno.desafioandroid.core.State
import dev.ribeiro.bruno.desafioandroid.core.State.*
import dev.ribeiro.bruno.desafioandroid.domain.entities.CharacterDetail
import dev.ribeiro.bruno.desafioandroid.domain.usecase.GetAllCharactersUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(private val getAllCharactersUseCase: GetAllCharactersUseCase) : ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: MutableLiveData<LoadingState>
        get() = _loadingState

    private val _charactersState = MutableLiveData<State<PagingData<CharacterDetail>>>()
    val charactersState: MutableLiveData<State<PagingData<CharacterDetail>>>
        get() = _charactersState

    fun getAllCharacters() {
        viewModelScope.launch {
            getAllCharactersUseCase.execute().cachedIn(this)
                .onStart {
                    _loadingState.postValue(LoadingState.ShowLoading)
                }
                .catch {
                    val exception = DefaultException("Não foi possível obter os dados necessários")
                    _charactersState.postValue(Error(exception))
                    _loadingState.postValue(LoadingState.HideLoading)
                }
                .collect { characters ->
                    _charactersState.postValue(Success(characters))
                    _loadingState.postValue(LoadingState.HideLoading)
                }
        }
    }
}