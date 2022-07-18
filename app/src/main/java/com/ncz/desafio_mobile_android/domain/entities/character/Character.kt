package com.ncz.desafio_mobile_android.domain.entities.character

import java.io.Serializable
import java.util.Date
import kotlin.collections.ArrayList

data class Character(
    val id: Int,
    var name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: ArrayList<Url>,
    val thumbnail: Image,
    val comics: List<ComicList>,
    val stories: List<StoryList>,
    val events: List<EventList>,
    val series: List<SeriesList>
):Serializable
