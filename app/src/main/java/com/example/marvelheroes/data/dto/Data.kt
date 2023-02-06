package com.example.marvelheroes.data.dto

data class Data(
    val offset: String?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<CharactersDto>?
)
