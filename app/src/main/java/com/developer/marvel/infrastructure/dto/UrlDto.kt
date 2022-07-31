package com.developer.marvel.infrastructure.dto

import com.developer.marvel.domain.entities.Url
import com.google.gson.annotations.SerializedName

data class UrlDto(
    @SerializedName("type")
    val type: String,

    @SerializedName("url")
    val url: String
) {

    fun mapperToEntity(): Url = Url(
        type = this.type,
        url = this.url
    )

}