package com.example.marvel_app.features.detail.remote.repository

import com.example.core.features.details.data.datasource.ComicRemoteDataSource
import com.example.core.features.details.data.repository.ComicRepository
import com.example.core.features.details.domain.Comic
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val comicRemoteDataSource: ComicRemoteDataSource
): ComicRepository {

    override suspend fun getComics(characterId: Int): List<Comic> {
        return comicRemoteDataSource.fetchComics(characterId)
    }
}