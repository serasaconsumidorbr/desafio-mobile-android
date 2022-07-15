package com.ncz.desafio_mobile_android.domain.entities

data class StoryList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<StorySummary>
)

