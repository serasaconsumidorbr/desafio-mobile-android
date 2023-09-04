package com.example.core.features.characters.data.datasource

import com.example.core.features.characters.domain.model.CharacterPaging

interface CharactersRemoteDatasource {

    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging
}