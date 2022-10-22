package com.example.marvel_characters.domain.models

data class Characters(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: CharacterImage
): java.io.Serializable {
    val image = thumbnail.path.substring(0, 4) + "s" +
            thumbnail.path.substring(4) +
            "." + thumbnail.extension
}