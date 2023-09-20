package com.marvelverse.data

import com.marvelverse.domain.MarvelCharacter
import com.marvelverse.domain.ThumbnailImage

class MarvelAPIResponseDTO(
    val code: String,
    val data: Data,
    val status: String,
) {
    fun getCharacters(): List<MarvelCharacter> {
        return this.data.results.map {
            val httpsThumbnailUrl = it.thumbnail.path.replace("http", "https")
            val thumbnail = ThumbnailImage(httpsThumbnailUrl, it.thumbnail.extension)
            MarvelCharacter(it.name, thumbnail, it.description)
        }
    }
}

data class Data(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<ResultDTO>,
    val total: String,
)

data class ResultDTO(
    val description: String,
    val id: String,
    val modified: String,
    val name: String,
    val thumbnail: Thumbnail,
)

data class Thumbnail(
    val extension: String,
    val path: String,
)