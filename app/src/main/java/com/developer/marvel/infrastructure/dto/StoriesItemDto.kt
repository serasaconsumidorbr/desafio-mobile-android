package com.developer.marvel.infrastructure.dto

import com.google.gson.annotations.SerializedName

data class StoriesItemDto(
    @SerializedName("resourceURI")
    val resourceURI: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("type")
    val type: String,
)