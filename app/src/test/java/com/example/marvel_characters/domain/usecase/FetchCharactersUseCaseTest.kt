package com.example.marvel_characters.domain.usecase

import com.example.marvel_characters.data.repository.CharacterRepository
import com.example.marvel_characters.helper.Stub
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

class FetchCharactersUseCaseTest {
    var repository = mockk<CharacterRepository>()
    private lateinit var useCase: FetchCharactersUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = FetchCharactersUseCase(repository)
    }

    @Test
    fun `When repository succeed usecase should return success`() {
        val resp = listOf(Stub.characterDTO, Stub.characterDTO.copy(id = 2))
        every { repository.retrieveCharacters(any()) } returns Single.just(CharacterRepository.Response.Success(resp))

        useCase.invoke(setOf(2)).test()
            .assertValue(FetchCharactersUseCase.Result.Success(listOf(Stub.character)))
    }

    @Test
    fun `When repository fails usecase should return error`() {
        every { repository.retrieveCharacters(any()) } returns Single.just(CharacterRepository.Response.Error)

        useCase.invoke(setOf(2)).test()
            .assertValue(FetchCharactersUseCase.Result.Error)
    }

    @Test
    fun `When repository returns end of list usecase should return indicate the same`() {
        every { repository.retrieveCharacters(any()) } returns Single.just(CharacterRepository.Response.EndOfList)

        useCase.invoke(setOf(2)).test()
            .assertValue(FetchCharactersUseCase.Result.EndOfList)
    }
}