package com.desafio.marvelapp.framework.remote

import com.desafio.marvelapp.framework.network.MarvelAPI
import com.desafio.marvelapp.framework.network.response.DataWrapperResponse
import com.project.core.data.repository.ICharactersRemoteDataSource
import javax.inject.Inject

class RetrofitCharactersDataSource @Inject constructor(
    private val marvelApp: MarvelAPI
) : ICharactersRemoteDataSource<DataWrapperResponse> {

    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse {
        return marvelApp.getCharacters(queries)
    }
}