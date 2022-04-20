package com.example.home_data.remote.mapper.impl

import com.example.home_data.remote.dto.CharacterDto
import com.example.home_data.remote.mapper.CharacterSafeStringMapper
import com.example.home_data.remote.mapper.CharacterDtoToCharacterMapper
import com.example.home_data.remote.mapper.CharacterThumbnailToImageUrlMapper
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.util.api.ImageVariant
import javax.inject.Inject

class CharacterDtoToCharacter @Inject constructor(
    private val thumbnailMapper: CharacterThumbnailToImageUrlMapper,
    private val safeStringMapper: CharacterSafeStringMapper,
    private val nullNameMessage: String,
    private val nullDescriptionMessage: String,
) : CharacterDtoToCharacterMapper {
    override fun mapFrom(
        dto: CharacterDto,
        imageVariant: ImageVariant,
        imageType: ImageVariant.Type,
    ): CharacterHomeUiModel = CharacterHomeUiModel(
        name = safeStringMapper.mapFrom(
            stringToMap = dto.name,
            safeValue = nullNameMessage
        ),
        imageUrl = thumbnailMapper.mapFrom(
            thumbnailDto = dto.thumbnail,
            imageVariant = imageVariant,
            imageType = imageType
        ),
        description = safeStringMapper.mapFrom(
            stringToMap = dto.description,
            safeValue = nullDescriptionMessage
        )
    )
}