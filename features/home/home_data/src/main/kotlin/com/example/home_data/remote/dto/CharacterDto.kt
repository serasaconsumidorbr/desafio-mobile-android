package com.example.home_data.remote.dto


import com.squareup.moshi.Json

data class CharacterDto(
    val name: String?,
    @Json(name = "thumbnail")
    val thumbnail: ThumbnailDto?,
)