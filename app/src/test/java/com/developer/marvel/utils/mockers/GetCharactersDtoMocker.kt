package com.developer.marvel.utils.mockers

import com.developer.marvel.infrastructure.dto.CharacterDto
import com.developer.marvel.infrastructure.dto.PresentationDto
import com.developer.marvel.infrastructure.dto.ThumbnailDto
import com.developer.marvel.infrastructure.dto.UrlDto

object GetCharactersDtoMocker {

    fun getCharactersMocker(): List<CharacterDto> {
        val charactersDto: ArrayList<CharacterDto> = arrayListOf()
        for (i in 0..10) {
            val characterDto = CharacterDto(
                i,
                "",
                "",
                "",
                "",
                listOf(UrlDto("", "")),
                ThumbnailDto("", ""),
                PresentationDto("", "", "", emptyList()),
                PresentationDto("", "", "", emptyList()),
                PresentationDto("", "", "", emptyList()),
                PresentationDto("", "", "", emptyList()),
            )

            charactersDto.add(characterDto)
        }

        return charactersDto
    }
}