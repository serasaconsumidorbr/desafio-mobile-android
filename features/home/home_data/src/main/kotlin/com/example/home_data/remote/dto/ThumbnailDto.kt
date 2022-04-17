package com.example.home_data.remote.dto


import com.squareup.moshi.Json

data class ThumbnailDto(
    @Json(name = "extension")
    val extension: String?,
    @Json(name = "path")
    val path: String?
)