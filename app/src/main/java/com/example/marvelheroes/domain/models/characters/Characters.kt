package com.example.marvelheroes.domain.models.characters

import java.io.Serializable
import java.util.*

data class Character(
    val id: Int,
    var name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: List<Url>,
    val thumbnail: Image,
    val comics: List<Comic>,
    val stories: List<Story>,
    val events: List<Event>,
    val series: List<Series>
): Serializable
