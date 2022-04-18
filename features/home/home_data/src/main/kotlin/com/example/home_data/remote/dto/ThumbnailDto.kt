package com.example.home_data.remote.dto


import com.squareup.moshi.Json

data class ThumbnailDto(
    @field:Json(name = "path") val path: String?,
)