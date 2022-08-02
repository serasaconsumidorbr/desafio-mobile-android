package com.developer.marvel.infrastructure.dto

import com.google.gson.annotations.SerializedName

open class PresentationItemDto(
    @SerializedName("resourceURI")
    val resourceURI: String,

    @SerializedName("name")
    val name: String
)