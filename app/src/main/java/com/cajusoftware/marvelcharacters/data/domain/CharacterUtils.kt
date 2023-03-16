package com.cajusoftware.marvelcharacters.data.domain

import com.cajusoftware.marvelcharacters.utils.toSafeUri

data class Thumbnail(
    val path: String?,
    val extension: String?
) {
    val thumbnailUri = path?.plus("/portrait_uncanny.")?.plus(extension)?.toSafeUri()
    val portraitMediumUri = path?.plus("/standard_fantastic.")?.plus(extension)?.toSafeUri()
    val uri = path?.plus(".")?.plus(extension)?.toSafeUri()
    val uriString = path?.plus(".")?.plus(extension)
}

data class Urls(
    val type: String?,
    val url: String?
)

data class Events(
    val available: Int?,
    val collectionUri: String?,
    val items: List<EventsSummary>?,
    val returned: Int?
)

data class EventsSummary(
    val name: String?,
    val resourceUri: String?
)

data class Comics(
    val available: Int?,
    val collectionUri: String?,
    val items: List<ComicSummary>?,
    val returned: Int?
)

data class ComicSummary(
    val name: String?,
    val resourceUri: String?
)

data class Series(
    val available: Int?,
    val collectionUri: String?,
    val items: List<SeriesSummary>?,
    val returned: Int?
)

data class SeriesSummary(
    val name: String?,
    val resourceUri: String?
)

data class Stories(
    val available: Int?,
    val collectionUri: String?,
    val items: List<StoriesSummary>?,
    val returned: Int?
)

data class StoriesSummary(
    val name: String?,
    val type: String?,
    val resourceUri: String?
)