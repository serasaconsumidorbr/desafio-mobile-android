package com.desafio.marvel.characters.ui.presenter

import com.nhaarman.mockitokotlin2.mock
import com.desafio.marvel.DumbData
import com.desafio.marvel.feature.characters.ui.presenter.CharactersPresenter
import com.desafio.marvel.feature.characters.ui.presenter.CharactersPresenterImpl
import com.desafio.marvel.feature.characters.ui.view.CharactersActivity
import com.desafio.marvel.feature.characters.ui.view.CharactersView
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CharactersPresenterTest {

    @Mock
    private lateinit var charactersPresenter: CharactersPresenter

    @Mock
    private var charactersView: CharactersView = mock()

    @Mock
    private var charactersActivity: CharactersActivity = mock()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        charactersPresenter = CharactersPresenterImpl(charactersActivity)
    }

    @Test
    fun `When called showCharacters`() {
        charactersPresenter.showCharacters(DumbData.getCharactersResponse())
        Assertions.assertThat(charactersPresenter.showCharacters(DumbData.getCharactersResponse())).isEqualTo(charactersView.showUsers(
            DumbData.getCharactersResponse()))
    }


    @Test
    fun `When called showErrorMessage`() {
        charactersPresenter.showErrorMessage("Error")
        Assertions.assertThat(charactersPresenter.showErrorMessage("Error")).isEqualTo(charactersView.showErrorMessage("Error"))
    }

    @Test
    fun `When called showErrorNetwork`() {
        charactersPresenter.showErrorNetwork()
        Assertions.assertThat(charactersPresenter.showErrorNetwork()).isEqualTo(charactersView.showErrorNetwork())
    }

}