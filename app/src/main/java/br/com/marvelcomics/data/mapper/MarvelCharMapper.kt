package br.com.marvelcomics.data.mapper

import br.com.marvelcomics.data.dto.MarvelCharDto
import br.com.marvelcomics.model.MarvelCharacter

fun MarvelCharDto.toMarvelCharacter(): MarvelCharacter = MarvelCharacter(
    id = id ?: 0L,
    name = name ?: "",
    description = description ?: "",
    thumbnail = "${thumbnail?.path}.${thumbnail?.extension}",
)