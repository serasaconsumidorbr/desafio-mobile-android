package com.example.marvelapp.features.characterdetail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.features.characterdetail.data.model.CharacterDetailModel
import com.example.marvelapp.features.characterdetail.data.repository.CharacterDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailRepository: CharacterDetailRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    private lateinit var characterObject: CharacterDetailModel
    private var fetchJob: Job? = null

    fun getCharacterDetail(id: Int) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(UiState.Loading)
            try {
                val model = characterDetailRepository.getCharacterDetail(id)
                if (!model.id.isNullOrEmpty()) {
                    characterObject = model
                    _uiState.postValue(UiState.Success(model))
                }
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error)
            }
        }
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: CharacterDetailModel) : UiState()
    object Error : UiState()
}