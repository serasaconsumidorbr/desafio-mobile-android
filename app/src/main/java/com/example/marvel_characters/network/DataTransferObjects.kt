package com.example.marvel_characters.network

import com.example.marvel_characters.database.entities.DatabaseCharacter
import com.example.marvel_characters.domain.MarvelCharacter
import com.google.gson.annotations.SerializedName


data class NetworkCharacterContainer(
    val data: Data
)

data class Data(
    @SerializedName("result")
    val networkCharacters: List<NetworkCharacter>
)

data class NetworkCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String
)

fun NetworkCharacterContainer.asDomainModel(): List<MarvelCharacter> {

    return data.networkCharacters.map {
        MarvelCharacter(
            it.id.toString(),
            it.name,
            it.description,
            "${it.thumbnail.path}.${it.thumbnail.extension}"
        )
    }
}

fun MarvelCharacter.asDatabaseModel() = DatabaseCharacter(
    id ?: "",
    name ?: "",
    description ?: "",
    thumbnailUrl ?: ""
)
