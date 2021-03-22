package com.drawiin.marvelcharacters.data.network.service

import com.drawiin.marvelcharacters.data.network.model.response.GetCharactersResponse
import com.drawiin.marvelcharacters.data.network.util.NetworkErrorHandler

class MarvelClient(
    private val marvelService: MarvelService
) : NetworkErrorHandler() {
    suspend fun getCharacters(
        apiKey: String,
        hash: String,
        timeStamp: Int,
        limit: Int,
        offset: Int
    ): GetCharactersResponse? {
        return requestScope {
            marvelService.getCharacters(apiKey, hash, timeStamp, limit, offset)
        }
    }
}