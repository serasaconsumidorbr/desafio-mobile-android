package com.desafio.marvel.feature.characters.ui.view

import com.desafio.marvel.feature.characters.domain.model.CharactersResponse

interface CharactersView {
    fun showUsers(list: CharactersResponse)
    fun showErrorMessage(message: String)
    fun showErrorNetwork()
}