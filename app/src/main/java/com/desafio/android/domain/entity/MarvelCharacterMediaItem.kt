package com.desafio.android.domain.entity

sealed class MarvelCharacterMediaItem {
    data class Comic(
        val resourceURI: String,
        val name: String
    ): MarvelCharacterMediaItem()

    data class Story(
        val resourceURI: String,
        val name: String,
        val type: String
    ): MarvelCharacterMediaItem()

    data class Event(
        val resourceURI: String,
        val name: String
    ): MarvelCharacterMediaItem()

    data class Series(
        val resourceURI: String,
        val name: String
    ): MarvelCharacterMediaItem()
}