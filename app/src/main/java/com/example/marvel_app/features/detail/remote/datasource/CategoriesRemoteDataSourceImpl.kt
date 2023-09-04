package com.example.marvel_app.features.detail.remote.datasource

import com.example.core.features.details.data.datasource.CategoriesRemoteDataSource
import com.example.core.features.details.domain.Comic
import com.example.core.features.details.domain.Event
import com.example.marvel_app.features.detail.response.toComicModel
import com.example.marvel_app.features.detail.response.toEventModel
import com.example.marvel_app.framework.network.MarvelApi
import javax.inject.Inject

class CategoriesRemoteDataSourceImpl @Inject constructor(
    private val marvelApi: MarvelApi
): CategoriesRemoteDataSource {

    override suspend fun fetchComics(characterId: Int): List<Comic> {
        return marvelApi.getComics(characterId).data.results.map {
            it.toComicModel()
        }
    }

    override suspend fun fetchEvents(characterId: Int): List<Event> {
        return marvelApi.getEvents(characterId).data.results.map {
            it.toEventModel()
        }
    }
}