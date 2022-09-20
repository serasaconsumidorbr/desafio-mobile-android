package com.example.home_data.remote.mapper.impl

import com.example.home_data.factories.CharacterDtoFactory
import com.example.home_data.remote.mapper.CharacterSafeStringMapper
import com.example.home_data.remote.mapper.CharacterThumbnailToImageUrlMapper
import com.example.home_domain.model.CharacterHomeUiModel
import com.example.util.api.ImageVariant
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

internal class CharacterDtoToCharacterTest {
    private val thumbnailToImageUrlMapper = mockk<CharacterThumbnailToImageUrlMapper>()
    private val safeStringMapper = mockk<CharacterSafeStringMapper>()

    private val mapper = CharacterDtoToCharacter(
        thumbnailMapper = thumbnailToImageUrlMapper,
        safeStringMapper = safeStringMapper,
        nullDescriptionMessage = "",
        nullNameMessage = ""
    )

    @Test
    fun mapper_dtoCharacter_returnsCharacterConverted() {
        val thumbUrl = "imageurl.com"
        val safeString = "safe string"
        every { thumbnailToImageUrlMapper.mapFrom(any(), any(), any()) } returns thumbUrl
        every { safeStringMapper.mapFrom(any(), any()) } returns safeString
        assertThat(
            mapper.mapFrom(
                dto = CharacterDtoFactory.make(),
                imageType = ImageVariant.Type.MEDIUM,
                imageVariant = ImageVariant.Standard
            )
        ).isEqualTo(
            CharacterHomeUiModel(
                name = safeString,
                description = safeString,
                imageUrl = thumbUrl
            )
        )
    }
}
