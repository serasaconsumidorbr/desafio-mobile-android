package com.developer.marvel.utils.mockers

import com.developer.marvel.domain.entities.*
import com.developer.marvel.infrastructure.dto.PresentationDto
import com.developer.marvel.infrastructure.dto.ThumbnailDto
import com.developer.marvel.infrastructure.dto.UrlDto
import java.util.*
import kotlin.collections.ArrayList

object GetCharactersMocker {

    fun getCharactersMocker(): List<Character> {
        val characters: ArrayList<Character> = arrayListOf()
        for (i in 0..10) {
            val characterDto = Character(
                i,
                "",
                "",
                Date(),
                "",
                listOf(Url("", "")),
                Thumbnail("", ""),
                Comics("", "", "", emptyList()),
                Stories("", "", "", emptyList()),
                Events("", "", "", emptyList()),
                Series("", "", "", emptyList()),
            )

            characters.add(characterDto)
        }

        return characters
    }
}