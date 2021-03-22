package com.drawiin.marvelcharacters.domain.model.character

data class Character(
    val description: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val thumbnail: Thumbnail? = null
)