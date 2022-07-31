package com.developer.marvel.domain.entities

data class Series (
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<SeriesItem>
)