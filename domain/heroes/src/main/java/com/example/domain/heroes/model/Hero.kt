package com.example.domain.heroes.model


data class Hero (
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
)

data class Thumbnail (
    val path: String,
    val extension: String
)
