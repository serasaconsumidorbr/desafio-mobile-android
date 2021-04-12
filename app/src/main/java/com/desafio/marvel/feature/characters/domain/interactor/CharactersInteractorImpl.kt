package com.desafio.marvel.feature.characters.domain.interactor

import android.content.Context
import com.desafio.marvel.commons.api.BaseCallback
import com.desafio.marvel.commons.connection.InternetConnection
import com.desafio.marvel.feature.characters.data.datasource.CharactersDataSource
import com.desafio.marvel.feature.characters.data.repository.CharactersRepository
import com.desafio.marvel.feature.characters.domain.model.CharactersResponse
import com.desafio.marvel.feature.characters.ui.presenter.CharactersPresenter

class CharactersInteractorImpl(
    private val mCharactersRepository: CharactersRepository,
    private val mCharactersPresenter: CharactersPresenter,
    private val mCharactersDataSource: CharactersDataSource,
    private val mContext: Context
) : CharactersInteractor {

    override fun getCharacters(offset: Int) {

        val status = mCharactersDataSource.statusCharacters()

        if (!status) {
            if (InternetConnection.isInternetAvailable(mContext)) {
                mCharactersRepository.getCharacters(
                    offset,
                    object : BaseCallback<CharactersResponse> {
                        override fun onSuccess(response: CharactersResponse) {
                            mCharactersPresenter.showCharacters(response)
                            mCharactersDataSource.saveCharacters(response)
                        }

                        override fun onError(error: String) {
                            mCharactersPresenter.showErrorMessage(error)
                        }
                    }
                )
            } else {
                mCharactersPresenter.showErrorNetwork()
            }

        } else {
            if (InternetConnection.isInternetAvailable(mContext)) {
                mCharactersRepository.getCharacters(
                    offset,
                    object : BaseCallback<CharactersResponse> {
                        override fun onSuccess(response: CharactersResponse) {
                            mCharactersPresenter.showCharacters(response)
                            if (offset == 20)
                                mCharactersDataSource.deleteCharacters()
                            else {
                                val local = mCharactersDataSource.getCharactersLocal()
                                mCharactersDataSource.saveCharacters(response.copy(results = response.results + local.results))
                            }
                        }

                        override fun onError(error: String) {
                            mCharactersPresenter.showErrorMessage(error)
                        }
                    }
                )
            } else {
                mCharactersPresenter.showCharacters(mCharactersDataSource.getCharactersLocal())
            }
        }

    }
}