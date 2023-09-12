package com.example.marvel_characters.network

import com.example.marvel_characters.Result
import com.example.marvel_characters.Samples.characterAIMWithCompleteData
import com.example.marvel_characters.TestWithKoinBase
import com.example.marvel_characters.domain.Character
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.io.File
import java.nio.charset.StandardCharsets

class CharactersRemoteDataSourceTest: TestWithKoinBase() {
    val listRequestBody =
        readJsonFromFile("/src/test/java/com/example/marvel_characters/resources/successful-characters-list-response-body.json")

    val pastRealSavedListResponse : Response<NetworkCharacterContainer> = Response.success(listRequestBody) as Response< NetworkCharacterContainer>
    //val pastRealSavedCharacterList = pastRealSavedListResponse.body()!!.data

    val characterRequestBody =         readJsonFromFile("/src/test/java/com/example/marvel_characters/resources/successful-character-response-body.json")
    val characterAIMResponse = Response.success(characterRequestBody) as Response<NetworkCharacterContainer>
    //val CharacterAIM = characterAIMResponse.body()!!.data


    lateinit var marvelApiServiceMock: MarvelApiService

    @Before
    override fun setup() {
        super.setup()
        MockitoAnnotations.openMocks(this)
        marvelApiServiceMock = Mockito.mock(MarvelApiService::class.java)
    }

    @Test
    fun shouldReturnNonEmptyCharactersListResult(): Unit = runBlocking {
        Mockito.`when`(marvelApiServiceMock.getCharacters(100, 0)).thenReturn(pastRealSavedListResponse)
        val remoteDataSource  = CharactersRemoteDataSource(marvelApiServiceMock)
        val resultData = remoteDataSource.getNextCharacterPage()

        assertThat(resultData, isA(Result.Success(listOf<Character>()).javaClass))
        assertThat((resultData as Result.Success).data.isNotEmpty(), `is`(true))
    }

    @Test
    fun shouldReturnExpectCharactersData(): Unit = runBlocking {
        Mockito.`when`(marvelApiServiceMock.getCharacters(100, 0)).thenReturn(characterAIMResponse)

        characterAIMWithCompleteData.let {
            val remoteDataSource  = CharactersRemoteDataSource(marvelApiServiceMock)

            val resultData = remoteDataSource.getCharacterById(it.id)

            val returnedCharacter = (resultData as Result.Success).data
            assertThat(returnedCharacter, equalTo(it))
        }
    }

    private fun readJsonFromFile(filePath: String): String {
        val userDir = System.getProperty("user.dir")
                val file = File(userDir + filePath)
        return file.readText(StandardCharsets.UTF_8)
    }
}