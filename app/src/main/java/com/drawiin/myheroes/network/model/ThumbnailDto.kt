package com.drawiin.myheroes.network.model


import com.drawiin.myheroes.domain.model.character.Thumbnail
import com.google.gson.annotations.SerializedName

data class ThumbnailDto(
    @SerializedName("extension")
    val extension: String? = null,
    @SerializedName("path")
    val path: String? = null
)