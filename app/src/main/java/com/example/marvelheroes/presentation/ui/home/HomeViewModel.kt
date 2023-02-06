package com.example.marvelheroes.presentation.ui.home

import com.example.marvelheroes.domain.services.HeroesService
import com.example.marvelheroes.data.extensions.reportErrorWarning
import com.example.marvelheroes.presentation.ui.baseViewModel.BaseViewModel
import com.example.marvelheroes.presentation.ui.baseViewModel.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.example.marvelheroes.domain.models.characters.*

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val heroesService: HeroesService
) : BaseViewModel(), CoroutineScope {

    private val _state = MutableStateFlow(HomeUi())
    val state = _state.asStateFlow()

    init {
        getHeroesMarvel()
    }

    private fun getHeroesMarvel() {
        exec(param = Unit,
            service = heroesService,
            shouldShowErrorMessage = false,
            onSuccessResult = { characterList ->
                _state.update { homeUi ->
                    homeUi.copy(
                        isLoading = false,
                        listHeroesVertical = filterListCharacters(characterList),
                        listHeroesHorizontally = listOfFiveCharacters(characterList)
                    )
                }
            },
            onError = { _, error, _ ->
                error?.let {
                    message.value = Event(error)
                    _state.update {
                        it.copy(isError = true, isLoading = false)
                    }
                }
                error
            })
    }

    private fun listOfFiveCharacters(characters: List<Character>): List<Character> {
        return characters.subList(0, 5)
    }

    private fun filterListCharacters(characters: List<Character>): List<Character> =
        characters.toSet().toList()
}