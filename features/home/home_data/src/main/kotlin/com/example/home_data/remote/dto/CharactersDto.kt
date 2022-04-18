package com.example.home_data.remote.dto
import com.squareup.moshi.Json

data class CharactersDto(
    @field:Json(name = "data") val data: CharactersDataDto
)