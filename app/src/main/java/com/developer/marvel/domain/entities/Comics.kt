package com.developer.marvel.domain.entities

data class Comics(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<ComicsItem>
)
