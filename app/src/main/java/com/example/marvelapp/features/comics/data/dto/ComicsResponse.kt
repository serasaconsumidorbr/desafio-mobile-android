package com.example.marvelapp.features.comics.data.dto

data class ComicsDataWrapper(
    val data: ComicData
)

data class ComicData(
    val results: List<ComicsDetails>
)

data class ComicsDetails(
    val id: String,
    val title: String,
    val pageCount: String,
    val thumbnail: ComicThumbnail
)

data class ComicThumbnail(
    val path: String,
    val extension: String
)