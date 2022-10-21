package com.example.marvel_characters.domain.repositories

import com.example.marvel_characters.domain.models.Characters

class CharactersRepositoryImpl(

) : CharactersRepository {

    override suspend fun getCharacters(): List<Characters> {
        TODO("Not yet implemented")
    }
}