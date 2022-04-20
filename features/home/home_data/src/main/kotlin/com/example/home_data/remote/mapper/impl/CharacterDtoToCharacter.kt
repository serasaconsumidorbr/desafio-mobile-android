package com.example.home_data.remote.mapper.impl

import com.example.home_data.remote.dto.CharacterDto
import com.example.home_data.remote.mapper.CharacterDtoToCharacterMapper
import com.example.home_data.remote.mapper.CharacterThumbnailToImageUrlMapper
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.util.api.ImageVariant
import javax.inject.Inject

class CharacterDtoToCharacter @Inject constructor(
    private val thumbnailMapper: CharacterThumbnailToImageUrlMapper,
) : CharacterDtoToCharacterMapper {
    override fun mapFrom(
        dto: CharacterDto,
        imageVariant: ImageVariant,
        imageType: ImageVariant.Type,
    ): CharacterHomeUiModel = CharacterHomeUiModel(
        name = dto.name.orEmpty(),
        imageUrl = thumbnailMapper.mapFrom(
            thumbnailDto = dto.thumbnail,
            imageVariant = imageVariant,
            imageType = imageType
        ),
        description = dto.description.orEmpty()
    )
}