package com.luisedu.marvel_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luisedu.marvel_app.data.repository.MarvelApiRepository
import com.luisedu.marvel_app.data.repository.MarvelApiServiceListener
import com.luisedu.marvel_app.model.MarvelApiResponse
import com.luisedu.marvel_app.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel (
    private var marvelRepository: MarvelApiRepository
    ) : ViewModel(), MarvelApiServiceListener {

    sealed class Loading {
        object ShowLoading : Loading()
        object HideLoading : Loading()
    }

    val loading = MutableLiveData<Loading>()
    val marvelCharacterResponse = MutableLiveData<List<Result>>()
    val errorLiveData = MutableLiveData<Throwable>()

    fun fetchCharactersList() {
        loading.value = Loading.ShowLoading
        CoroutineScope(Dispatchers.Main).launch {
            return@launch withContext(Dispatchers.Default) {
                marvelRepository.fetchCharactersList(
                    this@HomeViewModel
                )
            }
        }
    }

    override fun onSuccess(response: MarvelApiResponse) {
        loading.value = Loading.HideLoading
        CoroutineScope(Dispatchers.Main).launch {
            val charactersList = withContext(Dispatchers.Default) {
                response.data.results
            }
            marvelCharacterResponse.value = charactersList
        }
    }

    override fun onError(error: Throwable) {
        loading.value = Loading.HideLoading
        CoroutineScope(Dispatchers.Main).launch {
            val error = withContext(Dispatchers.Default) {
                error
            }
            errorLiveData.value = error
        }
    }
}