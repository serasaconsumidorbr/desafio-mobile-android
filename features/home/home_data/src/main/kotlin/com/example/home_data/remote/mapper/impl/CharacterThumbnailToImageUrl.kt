package com.example.home_data.remote.mapper.impl

import com.example.home_data.remote.dto.ThumbnailDto
import com.example.home_data.remote.mapper.CharacterThumbnailToImageUrlMapper
import com.example.home_data.remote.mapper.SslPathMapper
import com.example.util.api.ImageVariant
import javax.inject.Inject

class CharacterThumbnailToImageUrl @Inject constructor(
    private val sslPathMapper: SslPathMapper
) : CharacterThumbnailToImageUrlMapper {
    override fun mapFrom(
        thumbnailDto: ThumbnailDto?,
        imageVariant: ImageVariant,
        imageType: ImageVariant.Type,
    ): String = thumbnailDto?.let {
        "${sslPathMapper.map(it.path)}/${imageVariant.getValue(imageType)}.${it.extension}"
    }.orEmpty()
}
