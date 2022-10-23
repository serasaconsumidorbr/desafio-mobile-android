package com.example.marvel_characters.mock

import com.example.marvel_characters.domain.converters.CharactersConverter
import com.example.marvel_characters.domain.models.*

object AppMocks{
    private val characterImageMock = CharacterImage("http://mock", "jpg")
    val charactersListMock = listOf(
        Characters(id = 100L, "", "", characterImageMock),
        Characters(id = 101L, "", "", characterImageMock),
        Characters(id = 102L, "", "", characterImageMock),
        Characters(id = 103L, "", "", characterImageMock),
        Characters(id = 104L, "", "", characterImageMock),
        Characters(id = 105L, "", "", characterImageMock),
        Characters(id = 106L, "", "", characterImageMock),
        Characters(id = 107L, "", "", characterImageMock),
        Characters(id = 108L, "", "", characterImageMock),
        Characters(id = 109L, "", "", characterImageMock),
        Characters(id = 110L, "", "", characterImageMock),
        Characters(id = 111L, "", "", characterImageMock),
        Characters(id = 112L, "", "", characterImageMock),
        Characters(id = 113L, "", "", characterImageMock),
        Characters(id = 114L, "", "", characterImageMock),
        Characters(id = 115L, "", "", characterImageMock),
        Characters(id = 116L, "", "", characterImageMock),
        Characters(id = 117L, "", "", characterImageMock),
    )
    fun getCharactersEntityListMock(): List<CharacterEntity>{
        val result: ArrayList<CharacterEntity> = arrayListOf()
        charactersListMock.forEach {
            result.add(CharactersConverter.toEntity(it))
        }
        return result
    }
    val apiResponseMock = APIResponse(APIResults(charactersListMock))

}