package com.example.home_data.remote.mapper

import com.example.home_data.remote.dto.CharacterDto
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.util.Mapper
import com.example.util.api.ImageVariant

interface CharacterDtoToCharacterMapper {
    fun mapFrom(
        dto: CharacterDto,
        imageVariant: ImageVariant,
        imageType: ImageVariant.Type,
    ): CharacterHomeUiModel
}