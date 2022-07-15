package com.ncz.desafio_mobile_android.domain.entities

data class ComicList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<ComicSummary>
)