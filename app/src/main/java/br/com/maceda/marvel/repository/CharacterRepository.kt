package br.com.maceda.marvel.repository

import br.com.maceda.marvel.data.model.Character

interface CharacterRepository {

    suspend fun getCharacters(offset: Int = 0,
                     limit: Int = 10,
                     orderBy: String = "name"): ResponseRepositoryResult<List<Character>>
}
