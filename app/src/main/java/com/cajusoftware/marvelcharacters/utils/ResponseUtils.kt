package com.cajusoftware.marvelcharacters.utils

import com.cajusoftware.marvelcharacters.data.database.dtos.*
import com.cajusoftware.marvelcharacters.data.network.responses.*

fun CharacterResponse.asCharacterDto(
    copyright: String? = null,
    nextKey: Int? = null
): CharacterDto =
    CharacterDto(
        id = id,
        name = name,
        description = description,
        resourceUri = resourceUri,
        urls = urls?.asUrlDto(),
        thumbnail = thumbnail?.asThumbnailDto(),
        comics = comics?.asComicsDto(),
        stories = stories?.asStoriesDto(),
        events = events?.asEventsDto(),
        series = series?.asSeriesDto(),
        copyright = copyright,
        nextKey = nextKey
    )

fun List<UrlsResponse>.asUrlDto(): List<UrlsDto> =
    map { it.asUrlDto() }

fun UrlsResponse.asUrlDto(): UrlsDto = UrlsDto(type, url)

fun List<ComicSummaryResponse>.asComicsSummaryDto(): List<ComicSummaryDto> =
    map { it.asComicsSummaryDto() }

fun ComicSummaryResponse.asComicsSummaryDto(): ComicSummaryDto = ComicSummaryDto(name, resourceUri)

fun List<StoriesSummaryResponse>.asStoriesSummaryDto(): List<StoriesSummaryDto> =
    map { it.asStoriesSummaryDto() }

fun StoriesSummaryResponse.asStoriesSummaryDto(): StoriesSummaryDto =
    StoriesSummaryDto(name, type, resourceUri)

fun List<EventsSummaryResponse>.asEventsSummaryDto(): List<EventsSummaryDto> =
    map { it.asEventsSummaryDto() }

fun EventsSummaryResponse.asEventsSummaryDto(): EventsSummaryDto =
    EventsSummaryDto(name, resourceUri)

fun List<SeriesSummaryResponse>.asSeriesSummaryDto(): List<SeriesSummaryDto> =
    map { it.asSeriesSummaryDto() }

fun SeriesSummaryResponse.asSeriesSummaryDto(): SeriesSummaryDto =
    SeriesSummaryDto(name, resourceUri)

fun ThumbnailResponse.asThumbnailDto(): ThumbnailDto = ThumbnailDto(path, extension)

fun ComicsResponse.asComicsDto(): ComicsDto =
    ComicsDto(available, collectionUri, items?.asComicsSummaryDto(), returned)

fun StoriesResponse.asStoriesDto(): StoriesDto =
    StoriesDto(available, collectionUri, items?.asStoriesSummaryDto(), returned)

fun EventsResponse.asEventsDto(): EventsDto =
    EventsDto(available, collectionUri, items?.asEventsSummaryDto(), returned)

fun SeriesResponse.asSeriesDto(): SeriesDto =
    SeriesDto(available, collectionUri, items?.asSeriesSummaryDto(), returned)