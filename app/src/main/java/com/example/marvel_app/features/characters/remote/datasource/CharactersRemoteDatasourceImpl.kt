package com.example.marvel_app.features.characters.remote.datasource

import com.example.core.features.characters.data.datasource.CharactersRemoteDatasource
import com.example.marvel_app.framework.network.MarvelApi
import com.example.marvel_app.features.characters.response.characters.DataWrapperResponse
import javax.inject.Inject

class CharactersRemoteDatasourceImpl @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRemoteDatasource<DataWrapperResponse> {

    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse {
        return marvelApi.getCharacters(queries)
    }
}