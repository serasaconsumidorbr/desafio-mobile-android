package com.example.marvelheroes.data.repositories

import com.example.marvelheroes.data.datasource.CharactersRemoteDataSource
import com.example.marvelheroes.data.dto.ApiResponse
import com.example.marvelheroes.domain.repositories.ICharactersRepository
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val remoteDataSource : CharactersRemoteDataSource
) : ICharactersRepository {
    override suspend fun getCharactersAsync(
        limit: Int,
        ts: String,
        apikey: String,
        hash: String
    ): ApiResponse = remoteDataSource.getCharactersAsync(limit, ts, apikey, hash)
}