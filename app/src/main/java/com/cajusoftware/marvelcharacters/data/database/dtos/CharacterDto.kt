package com.cajusoftware.marvelcharacters.data.database.dtos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterDto(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    val description: String?,
    val resourceUri: String?,
    val urls: List<UrlsDto>?,
    val thumbnail: ThumbnailDto?,
    val comics: ComicsDto?,
    val stories: StoriesDto?,
    val events: EventsDto?,
    val series: SeriesDto?,
    val copyright: String? = null,
    val nextKey: Int? = null
)
