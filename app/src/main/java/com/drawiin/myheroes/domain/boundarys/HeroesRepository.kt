package com.drawiin.myheroes.domain.boundarys

import com.drawiin.myheroes.domain.model.character.Character

interface HeroesRepository {
    suspend fun getHeroes(apiKey: String, hash: String): List<Character>
}