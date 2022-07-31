package com.developer.marvel.infrastructure.dto

import com.developer.marvel.domain.entities.Thumbnail
import com.google.gson.annotations.SerializedName

data class ThumbnailDto (
    @SerializedName("path")
    val path: String,

    @SerializedName("extension")
    val extension: String
) {

    fun mapperToEntity(): Thumbnail = Thumbnail(
        path = this.path,
        extension = this.extension
    )
}