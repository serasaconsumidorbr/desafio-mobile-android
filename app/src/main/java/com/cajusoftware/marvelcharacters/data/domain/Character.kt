package com.cajusoftware.marvelcharacters.data.domain

data class Character(
    val id: Int,
    val name: String?,
    val description: String?,
    val resourceUri: String?,
    val urls: List<Urls>?,
    val thumbnail: Thumbnail?,
    val comics: Comics?,
    val stories: Stories?,
    val events: Events?,
    val series: Series?
)
