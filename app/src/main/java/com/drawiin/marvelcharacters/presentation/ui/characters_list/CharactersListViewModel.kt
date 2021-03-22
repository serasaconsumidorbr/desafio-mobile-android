package com.drawiin.marvelcharacters.presentation.ui.characters_list

import android.util.Log
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
    private val _charactersList by lazy { MutableLiveData<List<Character>>(emptyList()) }
    private val _charactersListLoading by lazy { MutableLiveData<Boolean>() }
    private val _dialog by lazy { MutableLiveData<String>() }

    private var currentPage: Int? = 0
    private var loading = false

    init {
        loadCarousel()
        loadNextPage()
    }

    fun reloadPage() {
        currentPage = 0
        clearData()
        loadCarousel()
        loadNextPage()
    }

    fun listScrolled(scrollY: Int, maxScroll: Int) {
        if (scrollY + VISIBLE_THRESHOLD >= maxScroll)
            loadNextPage()
    }

    private fun clearData() {
        _charactersCarousel.value = emptyList()
        _charactersList.value = emptyList()
    }

    private fun loadNextPage() {
        currentPage?.let { loadCharactersList(it) }
    }

    private fun loadCarousel() {
        charactersCarouselLoading.postValue(true)
        launchDataLoad(
            onFinish = { charactersCarouselLoading.postValue(false) },
            onFailure = ::handleError
        ) {
            val heroes = getCarousel.execute(apiKey, hash)
            _charactersCarousel.postValue(heroes)
        }
    }

    private fun loadCharactersList(page: Int) {
        charactersListLoading.postValue(true)
        launchDataLoad(
            onFinish = { charactersListLoading.postValue(false) },
            onFailure = ::handleError
        ) {
            if (!loading) {
                loading = true
                val heroes = getCharacters.execute(apiKey, hash, page)

                currentPage = if (heroes.isEmpty()) {
                    null
                } else {
                    page + 1
                }
                Log.d("VIEWMODEL", "Load Heros$page")
                _charactersList.postValue(_charactersList.value?.plus(heroes))
                loading = false
            }

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

    companion object {
        private const val VISIBLE_THRESHOLD = 600
    }
}