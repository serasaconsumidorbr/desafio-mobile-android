package com.example.home_data.remote.mapper.impl

import com.example.home_data.remote.dto.CharactersDataDto
import com.example.home_data.remote.mapper.CharacterDtoToCharacterMapper
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.util.api.ImageVariant
import javax.inject.Inject

class CharactersDataDtoToCharacters @Inject constructor(
    private val characterMapper: CharacterDtoToCharacterMapper,
) : CharactersDataDtoToCharactersMapper {

    override fun mapFrom(
        dto: CharactersDataDto,
        imageVariant: ImageVariant,
        imageType: ImageVariant.Type,
    ): List<CharacterHomeUiModel> = dto.results.map {
        characterMapper.mapFrom(
            dto = it,
            imageVariant = imageVariant,
            imageType = imageType
        )
    }
}