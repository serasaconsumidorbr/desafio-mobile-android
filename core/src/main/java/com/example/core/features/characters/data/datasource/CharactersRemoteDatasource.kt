package com.example.core.features.characters.data.datasource

interface CharactersRemoteDatasource<T> {

    suspend fun fetchCharacters(queries: Map<String, String>): T
}