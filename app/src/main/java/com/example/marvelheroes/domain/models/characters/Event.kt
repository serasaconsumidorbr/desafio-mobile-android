package com.example.marvelheroes.domain.models.characters

import java.io.Serializable

data class Event(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<EventSummary>,
) : Serializable

data class EventSummary(
    val resourceURI: String,
    val name: String,
) : Serializable
