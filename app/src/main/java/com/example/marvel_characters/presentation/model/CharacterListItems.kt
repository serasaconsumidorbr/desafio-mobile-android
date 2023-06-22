package com.example.marvel_characters.presentation.model

import androidx.annotation.StringRes

sealed class CharacterListItems {
    data class CarouselListModel(
        val characters: List<CharacterModel>
    ): CharacterListItems()

    data class CharacterListModel(
        val characters: List<CharacterModel>,
    ): CharacterListItems()

    data class TitleModel(
        @StringRes val textId: Int
    ): CharacterListItems()
}