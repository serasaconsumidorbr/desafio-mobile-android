package com.example.marvelheroes.domain.characters

import java.io.Serializable

data class Story(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<StorySummary>
) : Serializable

data class StorySummary(
    val resourceURI: String,
    val name: String,
    val type: String,
) : Serializable