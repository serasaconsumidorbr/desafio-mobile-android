package com.example.marvelheroes.domain.models.characters

import java.io.Serializable

data class Series(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<SeriesSummary>
) : Serializable

data class SeriesSummary(
    val resourceURI: String,
    val name: String,
) : Serializable