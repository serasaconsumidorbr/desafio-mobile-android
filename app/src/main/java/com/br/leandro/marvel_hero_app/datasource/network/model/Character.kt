package com.br.leandro.marvel_hero_app.datasource.network.model

import com.br.leandro.marvel_hero_app.datasource.db.model.CharacterEntity

data class Character(
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail,
    val description: String
) {

    fun toCharacterEntity() = CharacterEntity(
        id = id,
        name = name,
        imageUrl = thumbnail.iconImage()
    )
}

data class Characters(val data: Data)

data class Data(val results: List<Character>)