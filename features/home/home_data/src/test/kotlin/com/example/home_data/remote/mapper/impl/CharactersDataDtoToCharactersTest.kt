package com.example.home_data.remote.mapper.impl

import com.example.home_data.factories.CharacterDtoFactory
import com.example.home_data.factories.CharacterHomeUiModelFactory
import com.example.home_data.factories.CharactersDataDtoFactory
import com.example.home_data.remote.mapper.CharacterDtoToCharacterMapper
import com.example.util.api.ImageVariant
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

internal class CharactersDataDtoToCharactersTest {
    private val characterMapper = mockk<CharacterDtoToCharacterMapper>()
    private val mapper = CharactersDataDtoToCharacters(characterMapper)

    @Test
    fun mapper_dtoLists_returnsDtoMapped() {
        val characterHomeUiModel = CharacterHomeUiModelFactory.make()
        every { characterMapper.mapFrom(any(), any(), any()) } returns characterHomeUiModel

        val listToMap = (1..3).map { CharacterDtoFactory.make() }
        val expectedList = (1..3).map { characterHomeUiModel }

        assertThat(
            mapper.mapFrom(
                dto = CharactersDataDtoFactory.make(listToMap),
                imageVariant = ImageVariant.Standard,
                imageType = ImageVariant.Type.MEDIUM
            )
        ).isEqualTo(expectedList)
    }
}
