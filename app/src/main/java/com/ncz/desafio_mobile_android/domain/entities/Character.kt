package com.ncz.desafio_mobile_android.domain.entities

import java.util.*
import kotlin.collections.ArrayList

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: Date?,
    val resourceURI: String?,
    val urls: ArrayList<Url>,
    val thumbnail: String?,
    val comics: List<ComicList>,
    val stories: List<StoryList>,
    val events: List<EventList>,
    val series: List<SeriesList>
)
