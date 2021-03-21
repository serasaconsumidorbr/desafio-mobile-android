package com.drawiin.marvelcharacters.domain.interactors

import com.drawiin.marvelcharacters.base.BaseUTTest
import com.drawiin.marvelcharacters.data.network.model.CharacterDtoMapper
import com.drawiin.marvelcharacters.data.network.model.ThumbnailDtoMapper
import com.drawiin.marvelcharacters.data.network.service.MarvelClient
import com.drawiin.marvelcharacters.data.network.service.MarvelService
import com.drawiin.marvelcharacters.data.repository.DefaultCharactersRepository
import com.drawiin.marvelcharacters.domain.boundarys.CharactersRepository
import com.google.gson.GsonBuilder
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class GetCarouselTest : BaseUTTest() {

    private lateinit var apiService: MarvelService
    private lateinit var apiClient: MarvelClient
    private lateinit var repository: CharactersRepository

    @Before
    fun start() {
        super.setUp()
        apiService = Retrofit.Builder()
            .baseUrl(getMockWebServerUrl())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MarvelService::class.java)
        apiClient = MarvelClient(apiService)
        repository = DefaultCharactersRepository(
            apiClient,
            CharacterDtoMapper(
                ThumbnailDtoMapper()
            )
        )
    }

    @Test
    fun test_repository_get_characters_return_five_characters() {
        runBlocking(Dispatchers.IO) {
            mockNetworkResponseWithFileContent(
                "carousel_response_success.json",
                HttpURLConnection.HTTP_OK
            )
            val response = repository.getCharacters("", "", 5, 0)

            assertNotNull(response)
            assertEquals(response.size, 5)
            response.forEach { character ->
                assertNotNull(character)
            }
        }
    }
}