package com.example.marvel_app.features.detail.remote.repository

import com.example.core.features.details.data.datasource.CategoriesRemoteDataSource
import com.example.core.features.details.data.repository.CategoriesRepository
import com.example.core.features.details.domain.Comic
import com.example.core.features.details.domain.Event
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoriesRemoteDataSource: CategoriesRemoteDataSource
): CategoriesRepository {

    override suspend fun getComics(characterId: Int): List<Comic> {
        return categoriesRemoteDataSource.fetchComics(characterId)
    }

    override suspend fun getEvent(characterId: Int): List<Event> {
       return categoriesRemoteDataSource.fetchEvents(characterId)
    }

}