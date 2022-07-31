package com.developer.marvel.domain.entities

data class Events (
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<EventsItem>
)