package com.developer.marvel.infrastructure.dto

import com.google.gson.annotations.SerializedName

data class PresentationDto<T>(
    @SerializedName("available")
    val available: String,

    @SerializedName("returned")
    val returned: String,

    @SerializedName("collectionURI")
    val collectionURI: String,

    @SerializedName("items")
    val items: List<T>
)