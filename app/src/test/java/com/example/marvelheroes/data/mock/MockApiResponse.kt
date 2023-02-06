package com.example.marvelheroes.data.mock

import com.example.marvelheroes.data.dto.ApiResponse
import com.example.marvelheroes.data.dto.CharactersDto
import com.example.marvelheroes.data.dto.Data
import com.example.marvelheroes.domain.models.characters.*
import java.util.*

fun generateApiResponseMock() = ApiResponse(
    code = 200,
    status = "",
    copyright = "",
    attributionText = "",
    attributionHTML = "",
    etag = "",
    data = Data(
        offset = "",
        limit = 100,
        total = 100,
        count = 10,
        results = listOf(
            CharactersDto(
                id = 1011334,
                name = "3-D Man",
                description = "",
                modified = Date(),
                resourceURI = "http://gateway.marvel.com/v1/public/characters/1011334",
                urls = listOf(
                    Url(
                        type = "detail",
                        url = "http://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=853f44e003e2d58e82c205144d2ff349",
                    )
                ),
                thumbnail = Image(
                    path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                    extension = "jpg"
                ),
                comics = Comic(
                    available = 12,
                    returned = 12,
                    collectionURI = "http://gateway.marvel.com/v1/public/characters/1011334/comics",
                    items = listOf()
                ),
                stories = Story(
                    available = 21,
                    returned = 20,
                    collectionURI = "http://gateway.marvel.com/v1/public/characters/1011334/stories",
                    items = listOf()
                ),
                events = Event(
                    available = 0,
                    returned = 0,
                    collectionURI = "http://gateway.marvel.com/v1/public/characters/1016823/events",
                    items = listOf()
                ),
                series = Series(
                    available = 3,
                    returned = 3,
                    collectionURI = "http://gateway.marvel.com/v1/public/characters/1011334/series",
                    items = listOf()
                )
            )
        )
    )
)