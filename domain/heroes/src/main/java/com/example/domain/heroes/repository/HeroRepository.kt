package com.example.domain.heroes.repository

import com.example.domain.heroes.model.Hero
import kotlinx.coroutines.flow.Flow

interface HeroRepository {

    fun getHeroes(page : Int) : Flow<List<Hero>>
}