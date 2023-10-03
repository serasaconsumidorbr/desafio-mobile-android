package com.marvelverse.domain

data class MarvelCharacter(
    val characterName: String,
    val thumbnailImage: ThumbnailImage,
    val description: String = "",
)

data class ThumbnailImage(val url: String, val extension: String)