package com.desafio.marvel.feature.characters.data.datasource

import com.desafio.marvel.feature.characters.domain.model.CharactersResponse

interface CharactersDataSource {

    fun saveCharacters(mCharactersList: CharactersResponse): Boolean
    fun deleteCharacters(): Boolean
    fun getCharactersLocal(): CharactersResponse
    fun statusCharacters(): Boolean

}