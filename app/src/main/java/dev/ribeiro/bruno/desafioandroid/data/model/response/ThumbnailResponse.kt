package dev.ribeiro.bruno.desafioandroid.data.model.response

import com.google.gson.annotations.SerializedName

data class ThumbnailResponse(
    @SerializedName("extension")
    val extension: String,
    @SerializedName("path")
    val path: String
)
