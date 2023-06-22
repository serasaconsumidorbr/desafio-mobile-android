package com.example.marvel_characters.domain.usecase

import com.example.marvel_characters.data.repository.CharacterRepository
import com.example.marvel_characters.helper.Stub
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test


class GetHeaderUseCaseTest {

    var repository = mockk<CharacterRepository>()
    private lateinit var useCase: GetHeaderUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = GetHeaderUseCase(repository)
    }

    @Test
    fun `When repository returns success usecase should return success`() {
        val resp = listOf(Stub.characterDTO, Stub.characterDTO.copy(id = 2))
        every { repository.retrieveCharacters(any()) } returns Single.just(
            CharacterRepository.Response.Success(resp)
        )

        useCase.invoke(1).test()
            .assertValue(GetHeaderUseCase.Result.Success(listOf(Stub.character)))
    }

    @Test
    fun `When repository fails usecase should return error`() {
        every { repository.retrieveCharacters(any()) } returns Single.just(
            CharacterRepository.Response.Error
        )

        useCase.invoke(1).test()
            .assertValue(GetHeaderUseCase.Result.Error)
    }
}