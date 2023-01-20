package com.example.domain.heroes.repository

import com.example.domain.heroes.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(page : Int) : Flow<List<Character>>
}