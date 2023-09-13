package com.example.marvelapp.features.characterslist.data.dto

data class CharacterDataWrapper(
    val data: MarvelData
)

data class MarvelData(
    val results: List<CharacterDetails>
)

data class CharacterDetails(
    val id: String,
    val name: String,
    val comics: Comics,
    val series: Series,
    val description: String,
    val thumbnail: Thumbnail
)

data class Comics(
    val available: String
)

data class Series(
    val available: String
)

data class Thumbnail(
    val path: String,
    val extension: String
)