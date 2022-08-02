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
                id = i,
                name ="",
                description = "",
                modified =  Date(),
                urls = listOf(Url("", "")),
                thumbnail = Thumbnail("", ""),
                comics = Job(JobType.COMICS, 1, emptyList()),
                stories = Job(JobType.STORIES, 2, emptyList()),
                events = Job(JobType.EVENTS, 3, emptyList()),
                series = Job(JobType.SERIES,4, emptyList()),
            )

            characters.add(characterDto)
        }

        return characters
    }
}