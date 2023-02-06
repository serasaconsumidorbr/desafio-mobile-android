package com.example.marvelheroes.domain.repositories

import com.example.marvelheroes.data.dto.ApiResponse

interface ICharactersRepository {
    suspend fun getCharactersAsync(
        limit: Int,
        ts: String,
        apikey: String,
        hash: String,
    ): ApiResponse
}