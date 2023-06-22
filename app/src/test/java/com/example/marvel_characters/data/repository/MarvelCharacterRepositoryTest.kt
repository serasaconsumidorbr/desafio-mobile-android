package com.example.marvel_characters.data.repository

import com.example.marvel_characters.core.network.MarvelService
import com.example.marvel_characters.helper.Stub
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

class MarvelCharacterRepositoryTest {
    var service = mockk<MarvelService>(relaxUnitFun = true)
    var cache = mockk<CharacterCache>(relaxUnitFun = true)
    private lateinit var repository: MarvelCharacterRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        every { cache.getPage(any()) } returns null
        repository = MarvelCharacterRepository(service, cache)
    }

    @Test
    fun `When service returns result repository should return success`() {
        every {
            service.getCharacters(
                any(),
                any()
            )
        } returns Single.just(Stub.characterDataWrapperDTO)

        repository.retrieveCharacters(0).test()
            .assertValue(
                CharacterRepository.Response.Success(Stub.characterDataContainerDTO.results.orEmpty())
            )

        verify { cache.getPage(0) }
        verify { cache.savePage(0, Stub.characterDataWrapperDTO) }
    }

    @Test
    fun `When service fails repository should return error`() {
        every {
            service.getCharacters(
                any(),
                any()
            )
        } returns Single.error(Throwable())

        repository.retrieveCharacters(0).test()
            .assertValue(
                CharacterRepository.Response.Error
            )


        verify { cache.getPage(0) }
        verify(exactly = 0) { cache.savePage(0, Stub.characterDataWrapperDTO) }
    }

    @Test
    fun `When all items are fetched repository should indicate it`() {
        every {
            service.getCharacters(
                any(),
                any()
            )
        } returns Single.just(Stub.characterDataWrapperDTO)

        repository.retrieveCharacters(0)
            .flatMap {
                repository.retrieveCharacters(1000)
            }
            .test()
            .assertValue(CharacterRepository.Response.EndOfList)
    }
}