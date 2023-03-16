package com.cajusoftware.marvelcharacters.data.database.dtos

import androidx.room.ColumnInfo
import com.cajusoftware.marvelcharacters.utils.separator
import com.cajusoftware.marvelcharacters.utils.toDataString

abstract class BaseDto {
    protected val separator = String().separator
    abstract fun toDataString(): String
}

data class ThumbnailDto(
    val path: String?,
    val extension: String?
) : BaseDto() {
    override fun toDataString(): String {
        return "path=$path$separator" +
                "extension=$extension"
    }
}

data class UrlsDto(
    val type: String?,
    val url: String?
) : BaseDto() {
    override fun toDataString(): String {
        return "type=$type$separator" +
                "url=$url"
    }
}

data class EventsDto(
    val available: Int?,
    @ColumnInfo(name = "collection_uri") val collectionUri: String?,
    val items: List<EventsSummaryDto>?,
    val returned: Int?
) : BaseDto() {
    override fun toDataString(): String {
        return "available=$available$separator" +
                "collection_uri=$collectionUri$separator" +
                "items=${items?.toDataString()}$separator" +
                "returned=$returned"
    }
}

data class ComicsDto(
    val available: Int?,
    @ColumnInfo(name = "collection_uri") val collectionUri: String?,
    val items: List<ComicSummaryDto>?,
    val returned: Int?
) : BaseDto() {
    override fun toDataString(): String {
        return "available=$available$separator" +
                "collection_uri=$collectionUri$separator" +
                "items=${items?.toDataString()}$separator" +
                "returned=$returned"
    }
}

data class SeriesDto(
    val available: Int?,
    @ColumnInfo(name = "collection_uri") val collectionUri: String?,
    val items: List<SeriesSummaryDto>?,
    val returned: Int?
) : BaseDto() {
    override fun toDataString(): String {
        return "available=$available$separator" +
                "collection_uri=$collectionUri$separator" +
                "items=${items?.toDataString()}$separator" +
                "returned=$returned"
    }
}

data class StoriesDto(
    val available: Int?,
    @ColumnInfo(name = "collection_uri") val collectionUri: String?,
    val items: List<StoriesSummaryDto>?,
    val returned: Int?
) : BaseDto() {
    override fun toDataString(): String {
        return "available=$available$separator" +
                "collection_uri=$collectionUri$separator" +
                "items=${items?.toDataString()}$separator" +
                "returned=$returned"
    }
}

abstract class BaseSummaryDto(
    open val name: String?,
    @ColumnInfo(name = "resource_uri") open val resourceUri: String?,
    open val type: String? = null
)

data class EventsSummaryDto(
    override val name: String?,
    @ColumnInfo(name = "resource_uri") override val resourceUri: String?
) : BaseSummaryDto(name, resourceUri)

data class ComicSummaryDto(
    override val name: String?,
    @ColumnInfo(name = "resource_uri") override val resourceUri: String?
) : BaseSummaryDto(name, resourceUri)

data class SeriesSummaryDto(
    override val name: String?,
    @ColumnInfo(name = "resource_uri") override val resourceUri: String?
) : BaseSummaryDto(name, resourceUri)

data class StoriesSummaryDto(
    override val name: String?,
    override val type: String?,
    @ColumnInfo(name = "resource_uri") override val resourceUri: String?
) : BaseSummaryDto(name, resourceUri, type)