package com.luisedu.marvel_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luisedu.marvel_app.data.repository.MarvelApiRepository
import com.luisedu.marvel_app.data.repository.MarvelApiServiceListener
import com.luisedu.marvel_app.model.Data
import com.luisedu.marvel_app.model.MarvelApiResponse
import com.luisedu.marvel_app.model.Result

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
        marvelRepository.fetchCharactersList(
            this
        )
    }

    override fun onSuccess(response: MarvelApiResponse) {
        loading.value = Loading.HideLoading
        marvelCharacterResponse.value =
            if (response.data.results.isNullOrEmpty()) null else response.data.results
    }

    override fun onError(error: Throwable) {
        loading.value = Loading.HideLoading
        errorLiveData.value = error
    }
}