package com.desafio.android.ui.screen.home.content.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.desafio.android.core.base.execution.CaseExecution
import com.desafio.android.domain.entity.MarvelCharacter

class HomeScreenState {
    var characters: List<MarvelCharacter> by mutableStateOf(listOf())

    var getCharactersCaseExecution = CaseExecution()

    fun getBannerCharacters(): List<MarvelCharacter> {
        return if (characters.size >= 5) characters.subList(0, 4) else characters
    }

    fun getListCharacters(): List<MarvelCharacter> {
        return characters.filter { !getBannerCharacters().contains(it) }
    }

    var isLastItemVisible by mutableStateOf(false)
}