package com.desafio.android.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MarvelCharacter(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: List<MarvelCharacterUrl>,
    val thumbnail: MarvelCharacterThumbnail,
    val comics: MarvelCharacterMedia<MarvelCharacterMediaItem.Comic>,
    val stories: MarvelCharacterMedia<MarvelCharacterMediaItem.Story>,
    val events: MarvelCharacterMedia<MarvelCharacterMediaItem.Event>,
    val series: MarvelCharacterMedia<MarvelCharacterMediaItem.Series>,
) {
    fun getThumbnailUrl(): String {
        return "${thumbnail.path}.${thumbnail.extension}"
    }

    companion object {
        fun getMockedCharacter(): MarvelCharacter {
            return MarvelCharacter(
                id = 0,
                name = "John Doe",
                description = "",
                modified = Date(),
                resourceURI = "",
                urls = emptyList(),
                thumbnail = MarvelCharacterThumbnail(
                    path = "",
                    extension = "",
                ),
                comics = MarvelCharacterMedia(
                    available = 0,
                    returned = 0,
                    copyright = "",
                    collectionURI = "",
                    items = emptyList()
                ),
                stories = MarvelCharacterMedia(
                    available = 0,
                    returned = 0,
                    copyright = "",
                    collectionURI = "",
                    items = emptyList()
                ),
                events = MarvelCharacterMedia(
                    available = 0,
                    returned = 0,
                    copyright = "",
                    collectionURI = "",
                    items = emptyList()
                ),
                series = MarvelCharacterMedia(
                    available = 0,
                    returned = 0,
                    copyright = "",
                    collectionURI = "",
                    items = emptyList()
                )
            )
        }
    }
}