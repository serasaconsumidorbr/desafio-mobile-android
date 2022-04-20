package com.example.home_data.remote.repository

import com.example.home_data.factories.CharacterHomeUiModelFactory
import com.example.home_data.factories.CharactersDataDtoFactory
import com.example.home_data.remote.HomeApi
import com.example.home_data.remote.configs.CarouselConfig
import com.example.home_data.remote.dto.CharactersDto
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class HomeCarouselRepositoryImplTest {
    private val api = mockk<HomeApi>()
    private val charactersDataDtoToCharacters = mockk<CharactersDataDtoToCharactersMapper>()


    private val repository = HomeCarouselRepositoryImpl(
        api = api,
        charactersDataDtoToCharacters = charactersDataDtoToCharacters,
        carouselConfig = CarouselConfig(
            startIndex = 0,
            quantity = 0
        )
    )

    @Test
    fun repository_IOException_returnsNull() = runTest {
        coEvery { api.getCharacters(any(), any()) } throws IOException()
        Truth.assertThat(
            repository.getHomeCarouselCharacters()
        ).isNull()
    }

    @Test
    fun repository_HttpException_returnsNull() = runTest {
        coEvery { api.getCharacters(any(), any()) } throws HttpException(Response.success(null))
        Truth.assertThat(
            repository.getHomeCarouselCharacters()
        ).isNull()
    }

    @Test
    fun repository_noException_returnsResult() = runTest {
        coEvery { api.getCharacters(any(), any()) } returns CharactersDto(
            data = CharactersDataDtoFactory.make()
        )
        val mappedResult = listOf(CharacterHomeUiModelFactory.make())
        every { charactersDataDtoToCharacters.mapFrom(any(), any(), any()) } returns mappedResult
        Truth.assertThat(
            repository.getHomeCarouselCharacters()
        ).isEqualTo(mappedResult)
    }
}