package com.example.home_data.remote.mapper

import com.example.home_data.remote.dto.ThumbnailDto
import com.example.util.api.ImageVariant

interface CharacterThumbnailToImageUrlMapper {
    fun mapFrom(
        thumbnailDto: ThumbnailDto?,
        imageVariant: ImageVariant,
        imageType: ImageVariant.Type,
    ): String
}