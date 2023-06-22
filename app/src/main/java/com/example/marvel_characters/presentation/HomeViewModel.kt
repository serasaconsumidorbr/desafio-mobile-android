package com.example.marvel_characters.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvel_characters.R
import com.example.marvel_characters.core.RxProvider
import com.example.marvel_characters.domain.usecase.FetchCharactersUseCase
import com.example.marvel_characters.domain.usecase.GetHeaderUseCase
import com.example.marvel_characters.presentation.model.CharacterListItems
import com.example.marvel_characters.presentation.model.CharacterModel
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
    private val items = mutableListOf<CharacterListItems>()
    private val _output = MutableLiveData<State>()
    val output: LiveData<State>
        get() = _output

    init {
        loadItems()
    }

    fun setEvent(event: Event) {
        when (event) {
            is Event.Load -> loadItems()
            is Event.LoadMore -> loadCharacters()
        }
    }

    private fun loadItems() {
        _output.value = State.Loading
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
                if (it.first is GetHeaderUseCase.Result.Error && it.second is FetchCharactersUseCase.Result.Error) {
                    _output.postValue(State.Error)
                } else {
                    val items = mutableListOf<CharacterListItems?>()
                    items.add(getHeaderTitle(it.first))
                    items.add(getHeaderChars(it.first))
                    items.add(getCharactersTitle(it.second))
                    items.add(getCharacters(it.second))
                    this.items.addAll(items.filterNotNull())
                    _output.postValue(State.Content(this.items))
                }
            }, {
                _output.postValue(State.Error)
                Log.d("Error fetching chars:", it.message.orEmpty())
            })
    }

    private fun loadCharacters() {
        disposable?.dispose()
        disposable = fetchCharactersUseCase()
            .subscribeOn(rxProvider.io())
            .observeOn(rxProvider.mainThread())
            .subscribe({
                getCharacters(it)?.let {
                    val chars =
                        (items.last() as? CharacterListItems.CharacterListModel)?.characters.orEmpty()
                            .toMutableList()
                    chars.addAll(it.characters)
                    items.set(items.size - 1, CharacterListItems.CharacterListModel(chars))
                    _output.postValue(State.Content(items))
                }
            }, {
                Log.d("Error fetching chars:", it.message.orEmpty())
            })
    }

    private fun getHeaderChars(result: GetHeaderUseCase.Result): CharacterListItems? {
        if (result is GetHeaderUseCase.Result.Success) {
            return CharacterListItems.CarouselListModel(
                result.characters.map { CharacterModel(it.id, it.name, it.thumbnail) }
            )
        }
        return null
    }

    private fun getCharacters(result: FetchCharactersUseCase.Result): CharacterListItems.CharacterListModel? {
        if (result is FetchCharactersUseCase.Result.Success) {
            return CharacterListItems.CharacterListModel(
                result.characters.map { CharacterModel(it.id, it.name, it.thumbnail) }
            )
        }
        if (result is FetchCharactersUseCase.Result.EndOfList) {

        }
        return null
    }

    private fun getHeaderTitle(result: GetHeaderUseCase.Result): CharacterListItems.TitleModel? {
        if (result is GetHeaderUseCase.Result.Success) {
            return CharacterListItems.TitleModel(R.string.header_title)
        }
        return null
    }

    private fun getCharactersTitle(result: FetchCharactersUseCase.Result): CharacterListItems.TitleModel? {
        if (result is FetchCharactersUseCase.Result.Success) {
            return CharacterListItems.TitleModel(R.string.character_list_title)
        }
        return null
    }


    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    sealed class State {
        data class Content(
            val items: List<CharacterListItems>
        ) : State()

        object Error : State()

        object EndOfList: State()

        object Loading : State()
    }

    sealed class Event {
        object Load : Event()

        object LoadMore : Event()
    }

    companion object {
        private const val HEADER_SIZE: Int = 5
    }
}