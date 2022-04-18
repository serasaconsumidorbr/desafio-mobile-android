package com.example.home_data.remote.mapper.impl

import com.example.home_data.remote.dto.CharacterDto
import com.example.home_data.remote.mapper.CharacterDtoToCharacterMapper
import com.example.home_domain.model.Character

class CharacterDtoToCharacter : CharacterDtoToCharacterMapper {
    override fun mapFrom(from: CharacterDto): Character = Character(
        name = from.name.orEmpty(),
        imageUrl = from.thumbnail?.path.orEmpty()
    )
}