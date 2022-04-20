package com.example.home_data.remote.mapper.impl

import com.example.home_data.factories.ThumbnailDtoFactory
import com.example.util.api.ImageVariant
import com.google.common.truth.Truth
import org.junit.Test

class CharacterThumbnailToImageUrlTest {
    private val mapper = CharacterThumbnailToImageUrl()

    @Test
    fun mapper_nullDto_returnsEmptyString() = Truth.assertThat(
        mapper.mapFrom(
            thumbnailDto = null,
            imageType = ImageVariant.Type.LARGE,
            imageVariant = ImageVariant.Standard
        )
    ).isEmpty()

    @Test
    fun mapper_validDto_returnsImageUrl() {
        val path = "imagem"
        val extension = "gif"
        Truth.assertThat(
            mapper.mapFrom(
                thumbnailDto = ThumbnailDtoFactory.make(
                    path = path,
                    extension = extension
                ),
                imageVariant = ImageVariant.Portrait,
                imageType = ImageVariant.Type.SMALL
            )
        ).isEqualTo("${path}/portrait_small.${extension}")
    }
}