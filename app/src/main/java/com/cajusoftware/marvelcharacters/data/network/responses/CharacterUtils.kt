package com.cajusoftware.marvelcharacters.data.network.responses

import com.squareup.moshi.Json

data class ThumbnailResponse(
    val path: String?,
    val extension: String?
)

data class UrlsResponse(
    val type: String?,
    val url: String?
)

data class EventsResponse(
    val available: Int?,
    @Json(name = "collectionURI") val collectionUri: String?,
    val items: List<EventsSummaryResponse>?,
    val returned: Int?
)

data class EventsSummaryResponse(
    val name: String?,
    @Json(name = "resourceURI") val resourceUri: String?
)

data class ComicsResponse(
    val available: Int?,
    @Json(name = "collectionURI") val collectionUri: String?,
    val items: List<ComicSummaryResponse>?,
    val returned: Int?
)

data class ComicSummaryResponse(
    val name: String?,
    @Json(name = "resourceURI") val resourceUri: String?
)

data class SeriesResponse(
    val available: Int?,
    @Json(name = "collectionURI") val collectionUri: String?,
    val items: List<SeriesSummaryResponse>?,
    val returned: Int?
)

data class SeriesSummaryResponse(
    val name: String?,
    @Json(name = "resourceURI") val resourceUri: String?
)

data class StoriesResponse(
    val available: Int?,
    @Json(name = "collectionURI") val collectionUri: String?,
    val items: List<StoriesSummaryResponse>?,
    val returned: Int?
)

data class StoriesSummaryResponse(
    val name: String?,
    val type: String?,
    @Json(name = "resourceURI") val resourceUri: String?
)