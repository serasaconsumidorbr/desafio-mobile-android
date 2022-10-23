package com.example.home_data.remote.mapper

import com.example.home_data.remote.dto.CharactersDataDto
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.util.api.ImageVariant

interface CharactersDataDtoToCharactersMapper {
    fun mapFrom(
        dto: CharactersDataDto,
        imageVariant: ImageVariant,
        imageType: ImageVariant.Type,
    ): List<CharacterHomeUiModel>
}