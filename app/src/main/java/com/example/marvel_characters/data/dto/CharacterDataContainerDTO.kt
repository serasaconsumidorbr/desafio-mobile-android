package com.example.marvel_characters.data.dto

data class CharacterDataContainerDTO(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val results: List<CharacterDTO>?
)