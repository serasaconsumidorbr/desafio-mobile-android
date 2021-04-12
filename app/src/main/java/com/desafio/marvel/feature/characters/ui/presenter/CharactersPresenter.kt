package com.desafio.marvel.feature.characters.ui.presenter

import com.desafio.marvel.feature.characters.domain.model.CharactersResponse

interface CharactersPresenter {
    fun showCharacters(list: CharactersResponse)
    fun showErrorMessage(message: String)
    fun showErrorNetwork()
}