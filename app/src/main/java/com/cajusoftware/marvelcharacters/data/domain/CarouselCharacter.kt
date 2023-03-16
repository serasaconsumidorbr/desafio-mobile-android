package com.cajusoftware.marvelcharacters.data.domain

data class CarouselCharacter(
    val id: Int,
    val name: String?,
    val thumbnail: Thumbnail?,
    val copyright: String?,
    val comicsAmount: Int?,
    val eventsAmount: Int?,
    val seriesAmount: Int?,
    val storiesAmount: Int?,
)