package com.example.home_data.remote.mapper.impl

import com.example.home_data.remote.dto.ThumbnailDto
import com.example.home_data.remote.mapper.CharacterThumbnailToImageUrlMapper
import com.example.util.api.ImageVariant

class CharacterThumbnailToImageUrl: CharacterThumbnailToImageUrlMapper {
    override fun mapFrom(
        thumbnailDto: ThumbnailDto?,
        imageVariant: ImageVariant,
        imageType: ImageVariant.Type,
    ): String = thumbnailDto?.let {
        "${it.path}/${imageVariant.getValue(imageType)}.${it.extension}"
    }.orEmpty()
}