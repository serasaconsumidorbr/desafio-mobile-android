package com.example.util.api

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class ImageVariantTest {
    @Test
    fun imageVariant_returnsStandardMedium() = assertThat(
        ImageVariant.Standard.getValue(ImageVariant.Type.MEDIUM)
    ).isEqualTo("standard_medium")

    @Test
    fun imageVariant_returnsStandardLarge() = assertThat(
        ImageVariant.Standard.getValue(ImageVariant.Type.LARGE)
    ).isEqualTo("standard_large")

    @Test
    fun imageVariant_returnsLandscapeLarge() = assertThat(
        ImageVariant.Landscape.getValue(ImageVariant.Type.LARGE)
    ).isEqualTo("landscape_large")

}
