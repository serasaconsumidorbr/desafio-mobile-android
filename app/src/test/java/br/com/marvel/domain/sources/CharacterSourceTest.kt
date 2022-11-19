package br.com.marvel.domain.sources

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import br.com.marvel.data.MarvelApi
import br.com.marvel.data.models.CharacterResultsResponse
import br.com.marvel.data.models.DataResponse
import br.com.marvel.domain.models.Character
import br.com.marvel.domain.models.Thumbnail
import br.com.marvel.utilities.Constants
import br.com.marvel.utilities.Crypt
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class CharacterSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var marvelApi: MarvelApi
    lateinit var characterSource: CharacterSource

    companion object {
        private val characters = listOf(
            Character(
                id = 0,
                name = "",
                description = "",
                thumbnail = Thumbnail(path = "", extension = "")
            )
        )

        val dataResponse = DataResponse(data = CharacterResultsResponse(results = characters))

        private val nextCharacters = listOf(
            Character(
                id = 1,
                name = "",
                description = "",
                thumbnail = Thumbnail(path = "", extension = "")
            )
        )

        val nextDataResponse =
            DataResponse(data = CharacterResultsResponse(results = nextCharacters))

        const val publicKey = Constants.PUBLIC_KEY
        val ts = System.currentTimeMillis().toString()
        val hash = Crypt().md5Hash(ts, publicKey)
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        characterSource = CharacterSource(ts, publicKey, hash, marvelApi)
    }

    @Test
    fun pagingSourceRefreshSuccess() = runTest {
        given(marvelApi.getCharacters(ts, publicKey, hash, 20, 20)).willReturn(dataResponse)
        val expectedResult = dataResponse.data?.results?.let {
            PagingSource.LoadResult.Page(
                data = it.map { character ->
                    Character(
                        id = character.id,
                        name = character.name,
                        description = character.description,
                        thumbnail = character.thumbnail
                    )
                }, prevKey = null, nextKey = 2
            )
        }
        assertEquals(
            expectedResult, characterSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 1, loadSize = 1, placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun pagingSourceAppendSuccess() = runTest {
        given(marvelApi.getCharacters(ts, publicKey, hash, 20, 40)).willReturn(nextDataResponse)
        val expectedResult = nextDataResponse.data?.results?.let {
            PagingSource.LoadResult.Page(
                data = it.map { character ->
                    Character(
                        id = character.id,
                        name = character.name,
                        description = character.description,
                        thumbnail = character.thumbnail
                    )
                }, prevKey = 1, nextKey = 3
            )
        }
        assertEquals(
            expectedResult, characterSource.load(
                PagingSource.LoadParams.Append(
                    key = 2, loadSize = 1, placeholdersEnabled = false
                )
            )
        )
    }
}

