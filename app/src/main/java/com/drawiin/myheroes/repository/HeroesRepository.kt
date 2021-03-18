package com.drawiin.myheroes.repository

interface HeroesRepository {
    suspend fun getHeroes(apiKey: String, hash: String)
}