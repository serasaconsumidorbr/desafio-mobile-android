package com.example.marvelheroes.presentation.ui.home

import com.example.marvelheroes.domain.characters.Character
data class HomeUi(
    val isLoading : Boolean = true,
    val listHeroesVertical : List<Character> = emptyList(),
    val listHeroesHorizontally: List<Character> = emptyList()
)
