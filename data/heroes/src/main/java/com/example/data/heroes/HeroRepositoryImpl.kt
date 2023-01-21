package com.example.data.heroes

import com.example.data.heroes.local.dao.HeroDao
import com.example.data.heroes.remote.HeroesApi
import com.example.domain.heroes.model.Hero
import com.example.domain.heroes.repository.HeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HeroRepositoryImpl @Inject internal constructor(
    private val remoteDataSource: HeroesApi,
    private val localDataSource: HeroDao
) : HeroRepository {

    override fun getHeroes(page: Int): Flow<List<Hero>> = flow {

    }
}