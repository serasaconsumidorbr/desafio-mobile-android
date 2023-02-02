package com.example.marvelheroes.domain.characters

import java.io.Serializable

data class Comic(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<ComicSummary>
) : Serializable

data class ComicSummary(
    val resourceURI: String,
    val name: String,
) : Serializable