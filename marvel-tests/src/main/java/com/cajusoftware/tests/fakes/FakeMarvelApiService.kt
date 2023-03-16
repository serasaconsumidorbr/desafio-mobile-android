package com.cajusoftware.tests.fakes

import com.cajusoftware.marvelcharacters.data.network.responses.CharacterDataWrapperResponse
import com.cajusoftware.marvelcharacters.data.network.services.MarvelApiService
import com.cajusoftware.tests.fakes.FakeData.characterDataWrapperResponse

class FakeMarvelApiService : MarvelApiService {

    override suspend fun getCharacters(offset: Int): CharacterDataWrapperResponse {
        return characterDataWrapperResponse
    }
}