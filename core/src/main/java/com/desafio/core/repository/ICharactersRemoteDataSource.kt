package com.project.core.data.repository

interface ICharactersRemoteDataSource<T> {
    suspend fun fetchCharacters(queries: Map<String, String>): T
}