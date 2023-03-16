package com.cajusoftware.marvelcharacters.utils

import android.net.Uri
import androidx.core.net.toUri
import com.cajusoftware.marvelcharacters.data.database.dtos.*
import java.math.BigInteger
import java.security.MessageDigest

fun String.md5(): String {
    MessageDigest.getInstance("MD5").also {
        return BigInteger(1, it.digest(toByteArray())).toString(16).padStart(32, '0')
    }
}

fun String.toUrlsDto(): UrlsDto = UrlsDto(getType(), getUrl())

fun String.toEventsSummary(): EventsSummaryDto = EventsSummaryDto(getName(), getResourceUri())

fun String.toComicSummary(): ComicSummaryDto = ComicSummaryDto(getName(), getResourceUri())

fun String.toSeriesSummary(): SeriesSummaryDto = SeriesSummaryDto(getName(), getResourceUri())

fun String.toStoriesSummary(): StoriesSummaryDto =
    StoriesSummaryDto(getName(), getType(), getResourceUri())

fun String.toThumbnailDto(): ThumbnailDto = ThumbnailDto(getPath(), getExtension())

fun String.toEventsDto(): EventsDto {

    return EventsDto(
        getAvailable(),
        getCollectionUri(),
        getItems().toBaseSummaryDtoList<EventsSummaryDto>(),
        getReturned()
    )
}

fun String.toStoriesDto(): StoriesDto {
    return StoriesDto(
        getAvailable(),
        getCollectionUri(),
        getItems().toBaseSummaryDtoList<StoriesSummaryDto>(),
        getReturned()
    )
}

fun String.toSeriesDto(): SeriesDto {
    return SeriesDto(
        getAvailable(),
        getCollectionUri(),
        getItems().toBaseSummaryDtoList<SeriesSummaryDto>(),
        getReturned()
    )
}

fun String.toComicsDto(): ComicsDto {
    return ComicsDto(
        getAvailable(),
        getCollectionUri(),
        getItems().toBaseSummaryDtoList<ComicSummaryDto>(),
        getReturned()
    )
}

inline fun <reified T : BaseSummaryDto> String.toBaseSummaryDtoList(): List<T> {
    val array = this.split(")$separator")
    val pricesList = mutableListOf<T>()
    array.forEach {
        pricesList.add(it.toBaseSummaryDto())
    }
    return pricesList
}

inline fun <reified T : BaseSummaryDto> String.toBaseSummaryDto(): T {
    val name: String = this.substringAfter("name=").substringBefore(",")
    val resourceURI: String =
        this.substringAfter("resourceUri=").substringBefore(")").substringBeforeLast(",")
    val type: String = this.substringAfter("type=").substringBefore(",")

    return when (T::class) {
        EventsSummaryDto::class -> EventsSummaryDto(name, resourceURI) as T
        ComicSummaryDto::class -> ComicSummaryDto(name, resourceURI) as T
        StoriesSummaryDto::class -> StoriesSummaryDto(
            name = name,
            resourceUri = resourceURI,
            type = type
        ) as T
        else -> SeriesSummaryDto(name, resourceURI) as T
    }
}

private fun String.getAvailable() = this
    .substringAfter("available=")
    .substringBefore(separator)
    .toInt()

private fun String.getCollectionUri() = this
    .substringAfter("collection_uri=")
    .substringBefore(separator)

private fun String.getItems() = this
    .substringAfter("items=[")
    .substringBefore("]")

private fun String.getReturned() = this
    .substringAfter("returned=")
    .substringBefore(separator)
    .toInt()

private fun String.getName() = this
    .substringAfter("name=")
    .substringBefore(",")

private fun String.getResourceUri() = this
    .substringAfter("resource_uri=")
    .substringBefore(",")

private fun String.getType(delimiter: String = ",") = this
    .substringAfter("type=")
    .substringBefore(delimiter)

private fun String.getUrl() = this
    .substringAfter("url=")
    .substringBeforeLast(")")

private fun String.getExtension() = this
    .substringAfter("extension=")
    .substringBefore(separator)

private fun String.getPath() = this
    .substringAfter("path=")
    .substringBefore(separator)

val String.separator: String
    get() = "-_"

fun String.toSafeUri(): Uri? = try {
    this.toUri()
} catch (e: NullPointerException) {
    null
}