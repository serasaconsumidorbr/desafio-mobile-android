package com.developer.marvel.domain.entities

data class Stories (
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<StoriesItem>
)