package com.drawiin.marvelcharacters.presentation.ui.characters_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drawiin.marvelcharacters.data.network.model.error.NetworkError
import com.drawiin.marvelcharacters.domain.interactors.GetCarousel
import com.drawiin.marvelcharacters.domain.interactors.GetCharacters
import com.drawiin.marvelcharacters.domain.model.character.Character
import com.drawiin.marvelcharacters.utils.NAMED_API_KEY
import com.drawiin.marvelcharacters.utils.NAMED_HASH
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Named

class CharactersListViewModel @ViewModelInject constructor(
    private val getCarousel: GetCarousel,
    private val getCharacters: GetCharacters,
    @Named(NAMED_API_KEY) private val apiKey: String,
    @Named(NAMED_HASH) private val hash: String
) : ViewModel() {
    val charactersCarousel get() = _charactersCarousel
    val charactersCarouselLoading get() = _charactersCarouselLoading

    val charactersList get() = _charactersList
    val charactersListLoading get() = _charactersListLoading

    val dialog get() = _dialog

    private val _charactersCarousel by lazy { MutableLiveData<List<Character>>() }
    private val _charactersCarouselLoading by lazy { MutableLiveData<Boolean>() }

    private val _charactersList by lazy { MutableLiveData<List<Character>>() }
    private val _charactersListLoading by lazy { MutableLiveData<Boolean>() }

    private val _dialog by lazy { MutableLiveData<String>() }

    init {
        loadCarousel()
        loadCharactersList()
    }

    private fun loadCarousel() {
        launchDataLoad(
            onFinish = { charactersCarouselLoading.postValue(false) },
            onFailure = ::handleError
        ) {
            val heroes = getCarousel.execute(apiKey, hash)
            _charactersCarousel.postValue(heroes)
        }
    }

    private fun loadCharactersList() {
        launchDataLoad(
            onFinish = { charactersListLoading.postValue(false) },
            onFailure = ::handleError
        ) {
            val heroes = getCharacters.execute(apiKey, hash)
            _charactersList.postValue(heroes)
        }
    }

    private fun handleError(error: Throwable) {
        val message = getErrorMessage(error)
        dialog.postValue(message)
    }

    private fun launchDataLoad(
        onFinish: () -> Unit,
        onFailure: (Throwable) -> Unit,
        block: suspend () -> Unit
    ): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            try {
                block()
            } catch (error: Throwable) {
                onFailure(error)
            } finally {
                onFinish()
            }
        }
    }


    private fun getErrorMessage(throwable: Throwable): String? {
        return when (throwable) {
            is NetworkError -> throwable.errorMessage
            else -> "An unexpected error has occurred"
        }
    }
}