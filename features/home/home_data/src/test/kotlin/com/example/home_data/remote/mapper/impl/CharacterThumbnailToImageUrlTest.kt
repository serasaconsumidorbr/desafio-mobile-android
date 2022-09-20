package com.example.home_data.remote.mapper.impl

import com.example.home_data.factories.ThumbnailDtoFactory
import com.example.home_data.remote.mapper.SslPathMapper
import com.example.util.api.ImageVariant
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class CharacterThumbnailToImageUrlTest {

    companion object {
        private const val SSL_PATH_DEFAULT = "default"
        private const val EXTENSION = "gif"
    }

    private val sslPathMapper: SslPathMapper = mockk()
    private val mapper = CharacterThumbnailToImageUrl(sslPathMapper)

    @Before
    fun init() {
        every { sslPathMapper.map(any()) } returns SSL_PATH_DEFAULT
    }

    @Test
    fun mapper_nullDto_returnsEmptyString() {
        assertThat(
            mapper.mapFrom(
                thumbnailDto = null,
                imageType = ImageVariant.Type.LARGE,
                imageVariant = ImageVariant.Standard
            )
        ).isEmpty()
    }

    @Test
    fun mapper_validDto_returnsImageUrl() {
        assertThat(
            mapper.mapFrom(
                thumbnailDto = ThumbnailDtoFactory.make(
                    path = SSL_PATH_DEFAULT,
                    extension = EXTENSION
                ),
                imageVariant = ImageVariant.Portrait,
                imageType = ImageVariant.Type.SMALL
            )
        ).isEqualTo("${SSL_PATH_DEFAULT}/portrait_small.${EXTENSION}")
    }
}
