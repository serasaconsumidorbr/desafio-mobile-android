package com.example.home_data.remote.mapper.impl

import com.example.home_data.remote.dto.CharacterDto
import com.example.home_data.remote.mapper.CharacterDtoToCharacterMapper
import com.example.home_data.remote.mapper.CharacterThumbnailToImageUrlMapper
import com.example.home_domain.model.Character
import com.example.util.api.ImageVariant
import javax.inject.Inject

class CharacterDtoToCharacter @Inject constructor(
    private val thumbnailMapper: CharacterThumbnailToImageUrlMapper,
) : CharacterDtoToCharacterMapper {
    override fun mapFrom(from: CharacterDto): Character = Character(
        name = from.name.orEmpty(),
        imageUrl = thumbnailMapper.mapFrom(
            thumbnailDto = from.thumbnail,
            imageVariant = ImageVariant.Landscape,
            imageType = ImageVariant.Type.LARGE
        ),
        description = from.description.orEmpty()
    )
}