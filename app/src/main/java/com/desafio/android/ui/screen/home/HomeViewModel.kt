package com.desafio.android.ui.screen.home

import android.util.Log
import com.desafio.android.core.base.BaseViewModel
import com.desafio.android.core.base.execution.CaseResult
import com.desafio.android.domain.cases.home.GetMarvelCharactersCase
import com.desafio.android.ui.screen.home.content.state.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMarvelCharactersCase: GetMarvelCharactersCase
) : BaseViewModel<HomeInteraction, HomeScreenState>() {

    override val state: HomeScreenState = HomeScreenState()

    override fun handleUserInteraction(interaction: HomeInteraction) {
        when (interaction) {
            is HomeInteraction.GetCharacters -> handleGetCharacters()
            is HomeInteraction.ResetCharacters -> handleResetCharacters()
            is HomeInteraction.SetIsLastItemVisible -> handleSetIsLastItemVisible(
                isVisible = interaction.isVisible
            )
        }
    }

    init {
        interact(HomeInteraction.GetCharacters)
    }

    private fun handleSetIsLastItemVisible(isVisible: Boolean) {
        state.isLastItemVisible = isVisible
    }

    private fun handleResetCharacters() {
        state.characters = emptyList()
    }

    private fun handleGetCharacters() {
        if (state.getCharactersCaseExecution.isLoading()) return

        execute(
            case = {
                getMarvelCharactersCase(
                    offset = state.characters.size
                )
            },
            execution = state.getCharactersCaseExecution
        ) {
            when (it) {
                is CaseResult.Success -> {
                    state.characters = state.characters.toMutableList().apply { addAll(it.result) }.sortedBy { it.name }
                }

                is CaseResult.Failure -> {
                    handleError(it.throwable)
                }
            }
        }
    }
}