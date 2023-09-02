package com.example.marvel_app.utils.factory

import com.example.marvel_app.features.characters.response.characters.CharacterResponse
import com.example.marvel_app.features.characters.response.characters.DataContainerResponse
import com.example.marvel_app.features.characters.response.characters.DataWrapperResponse
import com.example.marvel_app.features.characters.response.characters.ThumbnailResponse

class DataWrapperResponseFactory {

    fun create() = DataWrapperResponse(
        copyright = "",
        data = DataContainerResponse(
            offset = 0,
            total = 2,
            results = listOf(
                CharacterResponse(
                    id = "1011334",
                    name = "3-D Man",
                    thumbnail = ThumbnailResponse(
                        path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                        extension = "jpg"
                    )
                ),
                CharacterResponse(
                    id = "1017100",
                    name = "A-Bomb (HAS)",
                    thumbnail = ThumbnailResponse(
                        path = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16",
                        extension = "jpg"
                    )
                )
            )
        )
    )
}