package com.example.marvel_characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvel_characters.core.RxProvider
import com.example.marvel_characters.domain.usecase.FetchCharactersUseCase
import com.example.marvel_characters.domain.usecase.GetHeaderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val getHeaderUseCase: GetHeaderUseCase,
    private val rxProvider: RxProvider,
) : ViewModel() {
    private var disposable: Disposable? = null
    private val _output = MutableLiveData<State>()
    val output: LiveData<State>
        get() = _output

    init {
        disposable?.dispose()
        disposable = getHeaderUseCase(HEADER_SIZE)
            .subscribeOn(rxProvider.io())
            .observeOn(rxProvider.mainThread())
            .flatMap({
                val ids = if (it is GetHeaderUseCase.Result.Success) {
                    it.characters.map { it.id }.toSet()
                } else setOf()
                fetchCharactersUseCase(ids)
            }, { header, items -> Pair(header, items) })
            .subscribe({
                //TODO handle response
            }, {
                //TODO handle error
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    sealed class State {
        //TODO define states
    }

    companion object {
        private const val HEADER_SIZE: Int = 5
    }
}