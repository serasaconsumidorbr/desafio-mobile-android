package com.developer.marvel.domain.entities

import java.io.Serializable
import java.util.*

data class Character(
    val id: Int,
    var name: String,
    val description: String,
    val modified: Date,
    val urls: List<Url>,
    val thumbnail: Thumbnail,
    val comics: Job,
    val stories: Job,
    val events: Job,
    val series: Job
) : Serializable