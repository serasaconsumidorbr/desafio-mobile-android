package com.example.home_data.remote.dto

import com.squareup.moshi.Json

data class CharactersDataDto(
    @field:Json(name = "results") val results: List<CharacterDto>
)
