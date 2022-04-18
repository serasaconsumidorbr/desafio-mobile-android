package com.example.home_data.remote.mapper.impl

import com.example.home_data.remote.dto.CharactersDataDto
import com.example.home_data.remote.mapper.CharacterDtoToCharacterMapper
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.example.home_domain.model.Character
import javax.inject.Inject

class CharactersDataDtoToCharacters @Inject constructor(
    private val characterMapper: CharacterDtoToCharacterMapper,
) : CharactersDataDtoToCharactersMapper {

    override fun mapFrom(from: CharactersDataDto): List<Character> = from.results.map {
        characterMapper.mapFrom(it)
    }
}