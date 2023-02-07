package com.desafio.android.domain.entity

data class MarvelCharacterMedia<T: MarvelCharacterMediaItem>(
    val available: Int,
    val returned: Int,
    val copyright: String,
    val collectionURI: String,
    val items: List<T>
)