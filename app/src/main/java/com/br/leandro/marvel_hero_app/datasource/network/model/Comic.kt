package com.br.leandro.marvel_hero_app.datasource.network.model

data class Comic(
    val id: Int,
    val title: String,
    val thumbnail: Thumbnail
)

data class Comics(val data: DataComic)

data class DataComic(val total: Int, val results: List<Comic>)