package com.example.marvel_characters.domain.models

data class Characters(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: CharacterImage
)
