package com.example.home_data.factories

import com.example.home_data.remote.dto.CharacterDto
import com.example.home_data.remote.dto.CharactersDataDto

object CharactersDataDtoFactory {
    fun make(
        results: List<CharacterDto> = listOf(CharacterDtoFactory.make())
    ): CharactersDataDto = CharactersDataDto(
        results = results
    )
}