package com.example.marvelheroes.data.dto

import com.example.marvelheroes.domain.characters.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class CharactersDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("modified") val modified: Date,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("urls") val urls: List<Url>,
    @SerializedName("thumbnail") val thumbnail: Image,
    @SerializedName("comics") val comics: Comic,
    @SerializedName("stories") val stories: Story,
    @SerializedName("events") val events: Event,
    @SerializedName("series") val series: Series

) : Serializable {
    fun mapToEntity(): Character = Character(
        id = this.id,
        name = this.name,
        description = this.description,
        modified = this.modified,
        resourceURI = this.resourceURI,
        urls = this.urls,
        thumbnail = this.thumbnail,
        comics = listOf(this.comics),
        stories = listOf(this.stories),
        events = listOf(this.events),
        series = listOf(this.series)
    )
}
