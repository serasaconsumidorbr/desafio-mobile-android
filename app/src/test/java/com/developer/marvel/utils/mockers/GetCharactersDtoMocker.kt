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
                PresentationDto(1, "", "", emptyList()),
                PresentationDto(2, "", "", emptyList()),
                PresentationDto(3, "", "", emptyList()),
                PresentationDto(4, "", "", emptyList()),
            )

            charactersDto.add(characterDto)
        }

        return charactersDto
    }
}