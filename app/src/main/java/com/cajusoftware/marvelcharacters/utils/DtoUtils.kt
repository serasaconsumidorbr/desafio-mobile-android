package com.cajusoftware.marvelcharacters.utils

import androidx.paging.PagingData
import androidx.paging.map
import com.cajusoftware.marvelcharacters.data.database.dtos.*
import com.cajusoftware.marvelcharacters.data.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun Flow<PagingData<CharacterDto>>.toCarouselCharacter(): Flow<PagingData<CarouselCharacter>> {
    return this.map { it.toCarouselCharacter() }
}

fun PagingData<CharacterDto>.toCarouselCharacter(): PagingData<CarouselCharacter> {
    return this.map { it.asCarouselCharacter() }
}

fun List<CharacterDto>.asCharacter(): List<Character> =
    map {
        it.asCharacter()
    }

fun CharacterDto.asCharacter(): Character =
    Character(
        id = id,
        name = name,
        description = description,
        resourceUri = resourceUri,
        urls = urls?.asUrls(),
        thumbnail = thumbnail?.asThumbnail(),
        comics = comics?.asComics(),
        stories = stories?.asStories(),
        events = events?.asEvents(),
        series = series?.asSeries()
    )

fun List<CharacterDto>.asCarouselCharacter(): List<CarouselCharacter> =
    map {
        it.asCarouselCharacter()
    }

fun CharacterDto.asCarouselCharacter(): CarouselCharacter =
    CarouselCharacter(
        id = id,
        name = name,
        thumbnail = thumbnail?.asThumbnail(),
        copyright = copyright,
        comicsAmount = comics?.items?.size,
        eventsAmount = events?.items?.size,
        seriesAmount = series?.items?.size,
        storiesAmount = stories?.items?.size,
    )

fun List<UrlsDto>.asUrls(): List<Urls> =
    map { it.asUrls() }

fun UrlsDto.asUrls(): Urls = Urls(type, url)

fun List<ComicSummaryDto>.asComicsSummary(): List<ComicSummary> = map { it.asComicsSummary() }

fun ComicSummaryDto.asComicsSummary(): ComicSummary = ComicSummary(name, resourceUri)

fun List<StoriesSummaryDto>.asStoriesSummary(): List<StoriesSummary> =
    map { it.asStoriesSummary() }

fun StoriesSummaryDto.asStoriesSummary(): StoriesSummary =
    StoriesSummary(name, type, resourceUri)

fun List<EventsSummaryDto>.asEventsSummary(): List<EventsSummary> =
    map { it.asEventsSummary() }

fun EventsSummaryDto.asEventsSummary(): EventsSummary = EventsSummary(name, resourceUri)

fun List<SeriesSummaryDto>.asSeriesSummary(): List<SeriesSummary> =
    map { it.asSeriesSummary() }

fun SeriesSummaryDto.asSeriesSummary(): SeriesSummary = SeriesSummary(name, resourceUri)

fun ThumbnailDto.asThumbnail(): Thumbnail = Thumbnail(path, extension)

fun ComicsDto.asComics(): Comics =
    Comics(available, collectionUri, items?.asComicsSummary(), returned)

fun StoriesDto.asStories(): Stories =
    Stories(available, collectionUri, items?.asStoriesSummary(), returned)

fun EventsDto.asEvents(): Events =
    Events(available, collectionUri, items?.asEventsSummary(), returned)

fun SeriesDto.asSeries(): Series =
    Series(available, collectionUri, items?.asSeriesSummary(), returned)