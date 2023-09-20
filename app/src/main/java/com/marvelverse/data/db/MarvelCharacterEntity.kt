package com.marvelverse.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marvelverse.domain.MarvelCharacter
import com.marvelverse.domain.ThumbnailImage

@Entity
data class MarvelCharacterEntity(
    @ColumnInfo(name = "character_name")
    val characterName: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "thumbnail_url")
    val thumbnailUrl: String,
    @ColumnInfo(name = "thumbnail_extension")
    val thumbnailExtension: String,
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    fun asDomainModel(): MarvelCharacter {
        return MarvelCharacter(characterName,
            ThumbnailImage(thumbnailUrl, thumbnailExtension),
            description)
    }
}

fun List<MarvelCharacterEntity>.asDomainModel(): List<MarvelCharacter> {
    return map {
        MarvelCharacter(it.characterName,
            ThumbnailImage(it.thumbnailUrl, it.thumbnailExtension),
            it.description)
    }
}