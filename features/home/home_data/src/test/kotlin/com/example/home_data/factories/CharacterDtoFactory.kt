package com.example.home_data.factories

import com.example.home_data.remote.dto.CharacterDto
import com.example.home_data.remote.dto.ThumbnailDto

object CharacterDtoFactory {
    fun make(
        name: String? = "name",
        description: String? = "description",
        thumbnailDto: ThumbnailDto? = null
    ) = CharacterDto(
        name = name,
        description = description,
        thumbnail = thumbnailDto
    )
}
