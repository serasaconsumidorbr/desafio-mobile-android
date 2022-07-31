package com.developer.marvel.domain.entities

import java.io.Serializable
import java.util.*

data class Character(
    val id: Int,
    var name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: List<Url>,
    val thumbnail: Thumbnail,
    val comics: Comics,
    val stories: Stories,
    val events: Events,
    val series: Series
):Serializable