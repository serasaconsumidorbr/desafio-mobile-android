package com.example.marvel_app.features.detail.remote.datasource

import com.example.core.features.details.data.datasource.ComicRemoteDataSource
import com.example.core.features.details.domain.Comic
import com.example.marvel_app.features.detail.response.comic.toComicModel
import com.example.marvel_app.framework.network.MarvelApi
import javax.inject.Inject

class ComicRemoteDataSourceImpl @Inject constructor(
    private val marvelApi: MarvelApi
): ComicRemoteDataSource {

    override suspend fun fetchComics(characterId: Int): List<Comic> {
        return marvelApi.getComics(characterId).data.results.map {
            it.toComicModel()
        }
    }
}