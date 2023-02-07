package com.desafio.android.domain.entity

data class MarvelCharacterDataWrapper(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: MarvelCharacterDataContainer,
    val etag: String
)