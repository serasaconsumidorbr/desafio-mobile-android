package com.drawiin.marvelcharacters.data.repository

import com.drawiin.marvelcharacters.base.BaseUTTest
import com.drawiin.marvelcharacters.data.network.model.CharacterDtoMapper
import com.drawiin.marvelcharacters.data.network.model.ThumbnailDtoMapper
import com.drawiin.marvelcharacters.data.network.model.error.NetworkError
import com.drawiin.marvelcharacters.data.network.service.MarvelClient
import com.drawiin.marvelcharacters.data.network.service.MarvelService
import com.drawiin.marvelcharacters.domain.boundarys.CharactersRepository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class DefaultCharactersRepositoryTest : BaseUTTest() {
    private lateinit var apiService: MarvelService
    private lateinit var apiClient: MarvelClient
    private lateinit var repository: CharactersRepository

    private val expectedResponseLength = 20

    @Before
    override fun setUp() {
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
                "characters_list_response_success.json",
                HttpURLConnection.HTTP_OK
            )
            val response = repository.getCharacters("", "", expectedResponseLength, 5)

            assertNotNull(response)
            assertEquals(response.size, expectedResponseLength)
            response.forEach { character ->
                assertNotNull(character)
            }
        }
    }

    @Test
    fun test_repository_io_error_throws_io_error() {
        runBlocking(Dispatchers.IO) {
            try {
                mockNetworkResponseWithError()
                val response = repository.getCharacters("", "", expectedResponseLength, 5)
            } catch (e: Exception) {
                assertThat(e, instanceOf(NetworkError.IOError::class.java))
            }
        }
    }
}