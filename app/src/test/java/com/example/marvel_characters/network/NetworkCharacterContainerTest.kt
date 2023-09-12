package com.example.marvel_characters.network

import com.example.marvel_characters.TestWithKoinBase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.java.KoinJavaComponent
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File
import java.nio.charset.StandardCharsets


class NetworkCharacterContainerTest : TestWithKoinBase() {
    lateinit var marvelApiService: MarvelApiService
    val requestBody =
        readJsonFromFile("/src/test/java/resources/successful-characters-list-response-body.json")


    @Before
    override fun setup() {
        super.setup()
        MockitoAnnotations.openMocks(this)
        marvelApiService = Mockito.mock(MarvelApiService::class.java)
    }

    @Test
    fun test() {

        val mockResponse = Response.success(requestBody) as Response<NetworkCharacterContainer>

        runBlocking {
            Mockito.`when`(marvelApiService.getCharacters(100, 0)).thenReturn(mockResponse)

            val networkCharacterContainer = requestBody
        }
    }

    private fun readJsonFromFile(filePath: String): String {
        val userDir = System.getProperty("user.dir")
        println(userDir)
        val file = File(userDir + filePath)
        return file.readText(StandardCharsets.UTF_8)
    }

}