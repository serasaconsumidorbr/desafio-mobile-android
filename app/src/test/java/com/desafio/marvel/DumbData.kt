package com.desafio.marvel

import com.desafio.marvel.feature.characters.domain.model.CharactersResponse
import com.desafio.marvel.feature.characters.domain.model.DataResponse
import com.desafio.marvel.feature.characters.domain.model.ResultsResponse
import com.desafio.marvel.feature.characters.domain.model.ThumbnailResponse


object DumbData {

    fun getThumbnailResponse() = ThumbnailResponse(
        "",
        ""
    )

    fun getResultsResponse() = ResultsResponse(
        1,
        "Abyes",
        "",
        getThumbnailResponse()
    )

    fun getCharactersResponse() = CharactersResponse(
        0,
        0,
        0,
        0,
        mutableListOf(
            getResultsResponse(),
            getResultsResponse()
        )

    )

    fun getDataResponse() = DataResponse(
        getCharactersResponse()
    )
}