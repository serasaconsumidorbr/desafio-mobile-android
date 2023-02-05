package com.example.marvelheroes.domain.models.characters

import java.io.Serializable

data class Story(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<StorySummary>
) : Serializable

data class StorySummary(
    val resourceURI: String,
    val name: String,
    val type: String,
) : Serializable