package com.developer.marvel.infrastructure.dto

import com.developer.marvel.domain.entities.*
import com.developer.marvel.utils.toDatetime
import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("urls") val urls: List<UrlDto>,
    @SerializedName("thumbnail") val thumbnail: ThumbnailDto,
    @SerializedName("comics") val comics: PresentationDto<PresentationItemDto>,
    @SerializedName("stories") val stories: PresentationDto<StoriesItemDto>,
    @SerializedName("events") val events: PresentationDto<PresentationItemDto>,
    @SerializedName("series") val series: PresentationDto<PresentationItemDto>

) {
    fun mapperToEntity(): Character = Character(
        id = this.id,
        name = this.name,
        description = this.description,
        modified = this.modified.toDatetime(),
        urls = this.urls.map { it.mapperToEntity() },
        thumbnail = this.thumbnail.mapperToEntity(),
        comics = mapperToComicsEntity(this.comics),
        stories = mapperToStoriesEntity(this.stories),
        events = mapperToEventsEntity(this.events),
        series = mapperToSeriesEntity(this.series)
    )

    private fun mapperToComicsEntity(comicsDto: PresentationDto<PresentationItemDto>): Job =
        Job(
            type = JobType.COMICS,
            available = comicsDto.available,
            items = comicsDto.items.map { JobItem(resourceURI = it.resourceURI, name = it.name) }
        )

    private fun mapperToEventsEntity(eventsDto: PresentationDto<PresentationItemDto>): Job =
        Job(
            type = JobType.EVENTS,
            available = eventsDto.available,
            items = eventsDto.items.map { JobItem(resourceURI = it.resourceURI, name = it.name) }
        )

    private fun mapperToSeriesEntity(seriesDto: PresentationDto<PresentationItemDto>): Job =
        Job(
            type = JobType.SERIES,
            available = seriesDto.available,
            items = seriesDto.items.map {
                JobItem(
                    resourceURI = it.resourceURI,
                    name = it.name
                )
            }
        )

    private fun mapperToStoriesEntity(storiesDto: PresentationDto<StoriesItemDto>): Job =
        Job(
            type = JobType.STORIES,
            available = storiesDto.available,
            items = storiesDto.items.map {
                JobItem(
                    resourceURI = it.resourceURI,
                    name = it.name,
                    type = JobItemType.toTypeObject(it.type)
                )
            },
        )
}

