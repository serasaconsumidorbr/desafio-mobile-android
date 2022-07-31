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
        resourceURI = this.resourceURI,
        urls = this.urls.map { it.mapperToEntity() },
        thumbnail = this.thumbnail.mapperToEntity(),
        comics = mapperToComicsEntity(this.comics),
        stories = mapperToStoriesEntity(this.stories),
        events = mapperToEventsEntity(this.events),
        series = mapperToSeriesEntity(this.series)
    )

    private fun mapperToComicsEntity(comicsDto: PresentationDto<PresentationItemDto>): Comics =
        Comics(
            available = comicsDto.available,
            returned = comicsDto.returned,
            collectionURI = comicsDto.collectionURI,
            items = comicsDto.items.map { ComicsItem(resourceURI = it.resourceURI, name = it.name) }
        )

    private fun mapperToEventsEntity(eventsDto: PresentationDto<PresentationItemDto>): Events =
        Events(
            available = eventsDto.available,
            returned = eventsDto.returned,
            collectionURI = eventsDto.collectionURI,
            items = eventsDto.items.map { EventsItem(resourceURI = it.resourceURI, name = it.name) }
        )

    private fun mapperToSeriesEntity(serriesDto: PresentationDto<PresentationItemDto>): Series =
        Series(
            available = serriesDto.available,
            returned = serriesDto.returned,
            collectionURI = serriesDto.collectionURI,
            items = serriesDto.items.map {
                SeriesItem(
                    resourceURI = it.resourceURI,
                    name = it.name
                )
            }
        )

    private fun mapperToStoriesEntity(storiesDto: PresentationDto<StoriesItemDto>): Stories =
        Stories(
            available = storiesDto.available,
            returned = storiesDto.returned,
            collectionURI = storiesDto.collectionURI,
            items = storiesDto.items.map {
                StoriesItem(
                    resourceURI = it.resourceURI,
                    name = it.name,
                    type = StoriesItemType.toTypeObject(it.type)
                )
            },
        )
}

