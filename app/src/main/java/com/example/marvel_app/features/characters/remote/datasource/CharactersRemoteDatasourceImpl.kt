package com.example.marvel_app.features.characters.remote.datasource

import com.example.core.features.characters.data.datasource.CharactersRemoteDatasource
import com.example.core.features.characters.domain.model.CharacterPaging
import com.example.marvel_app.framework.network.MarvelApi
import com.example.marvel_app.features.characters.response.toCharacterModel
import javax.inject.Inject

class CharactersRemoteDatasourceImpl @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRemoteDatasource {

    override suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging {
        val data = marvelApi.getCharacters(queries).data
        val characters = data.results.map {
            it.toCharacterModel()
        }
        return CharacterPaging(
            data.offset,
            data.total,
            characters
        )
    }
}