package com.drawiin.myheroes.repository

import com.drawiin.myheroes.domain.model.character.Character

interface HeroesRepository {
    suspend fun getHeroes(apiKey: String, hash: String): List<Character>
}