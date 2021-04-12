package com.desafio.marvel.feature.characters.data.repository

import com.desafio.marvel.commons.api.BaseCallback
import com.desafio.marvel.feature.characters.domain.model.CharactersResponse

interface CharactersRepository {

    fun getCharacters(offset: Int, callback: BaseCallback<CharactersResponse>)
}