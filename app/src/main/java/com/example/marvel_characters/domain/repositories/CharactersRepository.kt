package com.example.marvel_characters.domain.repositories

import com.example.marvel_characters.domain.models.Characters

interface CharactersRepository {

    suspend fun getCharacters(): List<Characters>

}