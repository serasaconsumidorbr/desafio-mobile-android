package com.example.marvel_characters.domain.model

data class CharacterDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val results: List<Character>
)