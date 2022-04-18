package com.example.home_data.remote.dto

import com.squareup.moshi.Json

data class CharacterDto(
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "thumbnail") val thumbnail: ThumbnailDto?,
)