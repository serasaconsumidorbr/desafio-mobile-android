package com.desafio.marvel.feature.characters.ui.presenter

import com.desafio.marvel.feature.characters.domain.model.CharactersResponse
import com.desafio.marvel.feature.characters.ui.view.CharactersView

class CharactersPresenterImpl(private val mView: CharactersView) : CharactersPresenter {
    override fun showCharacters(list: CharactersResponse) {
        mView.showUsers(list)
    }

    override fun showErrorMessage(message: String) {
        mView.showErrorMessage(message)
    }

    override fun showErrorNetwork() {
        mView.showErrorNetwork()
    }
}