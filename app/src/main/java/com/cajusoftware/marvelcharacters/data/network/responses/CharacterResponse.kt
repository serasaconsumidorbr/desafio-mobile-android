package com.cajusoftware.marvelcharacters.data.network.responses

import com.squareup.moshi.Json

data class CharacterResponse(
    val id: Int,
    val name: String?,
    val description: String?,
    val modified: String?,
    @Json(name = "resourceURI") val resourceUri: String?,
    val urls: List<UrlsResponse>?,
    val thumbnail: ThumbnailResponse?,
    val comics: ComicsResponse?,
    val stories: StoriesResponse?,
    val events: EventsResponse?,
    val series: SeriesResponse?
)
