package com.example.marvel_app.features.detail.response

import androidx.annotation.StringRes

data class DetailChildViewEntity (
    val id: Int,
    val imageUrl: String,
)

data class DetailParentViewEntity (
    @StringRes
    val categoryStringResId: Int,
    val detailChildList: List<DetailChildViewEntity> = listOf()
)