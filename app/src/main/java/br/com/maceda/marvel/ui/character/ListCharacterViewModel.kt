package br.com.maceda.marvel.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.maceda.marvel.data.model.Character
import br.com.maceda.marvel.repository.ResponseRepositoryResult
import br.com.maceda.marvel.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private var _list: MutableLiveData<List<Character>> = MutableLiveData()
    val list: LiveData<List<Character>> = _list
    private var _listFirstCharacters: MutableLiveData<List<Character>> = MutableLiveData()
    val listFirstCharacters : LiveData<List<Character>> = _listFirstCharacters
    private var _progressBarVisibility : MutableLiveData<Boolean> = MutableLiveData(true)
    val progressBarVisibility : LiveData<Boolean> = _progressBarVisibility
    private var _error : MutableLiveData<Pair<Boolean,String?>> = MutableLiveData()
    val error: LiveData<Pair<Boolean,String?>> = _error

    fun loadCharacters(offset: Int = 0) = viewModelScope.launch{
        _progressBarVisibility.value = true

        loadCharactersCarousel()

        val results = repository.getCharacters(offset = offset + NUMBER_FIRST_CHARACTERS)
        handleResultCharacters(results){
            if (results is ResponseRepositoryResult.Success) {
                _list.value = if (_list.value == null) {
                    results.value
                } else {
                    _list.value?.plus(results.value)
                }
            }
        }

        _progressBarVisibility.value = false
    }

    private fun loadCharactersCarousel() = viewModelScope.launch{
        val results = repository.getCharacters(offset = 0, limit = NUMBER_FIRST_CHARACTERS)
        handleResultCharacters(results) {
            if (results is ResponseRepositoryResult.Success) {
                if (_listFirstCharacters.value == null)
                    _listFirstCharacters.value = results.value ?: listOf()
            }
        }
    }

    private fun handleResultCharacters(
        results: ResponseRepositoryResult<List<Character>>,
        success: () -> Unit
    ) {
        when (results) {
            is ResponseRepositoryResult.Success -> {
                success()
                _error.value = Pair(false, null)
            }
            is ResponseRepositoryResult.Error -> {
                _error.value = Pair(true,results.message)
            }
            is ResponseRepositoryResult.ErrorException -> {
                _error.value = Pair(true,results.e.message)
            }
        }
    }

    companion object {
        const val NUMBER_FIRST_CHARACTERS = 5
    }
}