package com.updeveloped.desafiomarvel.data.response.dto

import com.google.gson.annotations.SerializedName

data class ThumbnailResponse(
    @SerializedName("extension")
    val extension: String,
    @SerializedName("path")
    val path: String
)