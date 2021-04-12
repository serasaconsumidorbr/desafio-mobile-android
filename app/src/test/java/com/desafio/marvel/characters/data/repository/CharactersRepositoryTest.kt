package com.desafio.marvel.characters.data.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.desafio.marvel.DumbData
import com.desafio.marvel.commons.api.ApiClient
import com.desafio.marvel.commons.api.BaseCallback
import com.desafio.marvel.feature.characters.data.api.CharactersService
import com.desafio.marvel.feature.characters.data.repository.CharactersRepository
import com.desafio.marvel.feature.characters.data.repository.CharactersRepositoryImpl
import com.desafio.marvel.feature.characters.domain.model.CharactersResponse
import com.desafio.marvel.feature.characters.domain.model.DataResponse
import okhttp3.internal.EMPTY_RESPONSE
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response



class CharactersRepositoryTest {

    @Mock
    private val mClient = ApiClient()

    private lateinit var mRepository: CharactersRepository

    @Mock
    private var callback: BaseCallback<CharactersResponse> = mock()

    @Before
    fun setup() {

        mRepository = CharactersRepositoryImpl(mClient)
    }

    @Test
    fun getCharacters_ok() {
        mRepository.getCharacters(0, callback)

        Assertions.assertThat(mRepository.getCharacters(0, callback)).isEqualTo(Unit)

        val response = Response.success(DumbData.getDataResponse())

        val mockCall = mock<Call<DataResponse>> {
            on { execute() } doReturn Response.success(response.body())
        }

        val mockApiService = mock<CharactersService> {
            on { getCharacters(10,0) } doReturn mockCall
        }

        Mockito.`when`(mockApiService.getCharacters(10,0)).thenReturn(mockCall)

    }

    @Test
    fun getCharacters_error() {
        mRepository.getCharacters(0, callback)

        Assertions.assertThat(mRepository.getCharacters(0, callback)).isEqualTo(Unit)


        val mockCall = mock<Call<DataResponse>> {
            on { execute() } doReturn Response.error(500, EMPTY_RESPONSE)
        }

        val mockApiService = mock<CharactersService> {
            on { getCharacters(10,0) } doReturn mockCall
        }

        Mockito.`when`(mockApiService.getCharacters(10, 0)).thenReturn(mockCall)

    }
}