package com.example.marvelheroes.data.repositories

import com.example.marvelheroes.data.datasource.CharactersRemoteDataSource
import com.example.marvelheroes.data.dto.ApiResponse
import com.example.marvelheroes.domain.repositories.ICharactersRepository

class CharactersRepository : ICharactersRepository {
    override suspend fun getCharactersAsync(
        limit: Int,
        ts: String,
        apikey: String,
        hash: String
    ): ApiResponse = CharactersRemoteDataSource.getCharactersAsync(limit, ts, apikey, hash)
}