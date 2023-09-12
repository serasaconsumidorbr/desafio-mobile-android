package com.example.marvel_characters.network

import com.example.marvel_characters.database.entities.DatabaseCharacter
import com.example.marvel_characters.domain.Character
import com.google.gson.annotations.SerializedName


data class NetworkCharacterContainer(
    val data: Data,
    val copyright: String,
    val attributionText: String
)

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    @SerializedName("results")
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

fun NetworkCharacterContainer.asDomainModel(): List<Character> {

    return data.networkCharacters.map {
        Character(
            it.id.toString(),
            it.name,
            it.description,
            "${it.thumbnail.path}.${it.thumbnail.extension}"
        )
    }
}

fun Character.asDatabaseModel() = DatabaseCharacter(
    id,
    name,
    description,
    thumbnailUrl
)
