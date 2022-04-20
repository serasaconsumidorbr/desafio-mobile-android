package com.example.util.api

import com.google.common.truth.Truth
import org.junit.Test

internal class ImageVariantTest {
    @Test
    fun imageVariant_returnsStandardMedium() = Truth.assertThat(
        ImageVariant.Standard.getValue(ImageVariant.Type.MEDIUM)
    ).isEqualTo("standard_medium")

    @Test
    fun imageVariant_returnsStandardLarge() = Truth.assertThat(
        ImageVariant.Standard.getValue(ImageVariant.Type.LARGE)
    ).isEqualTo("standard_large")

    @Test
    fun imageVariant_returnsLandscapeLarge() = Truth.assertThat(
        ImageVariant.Landscape.getValue(ImageVariant.Type.LARGE)
    ).isEqualTo("landscape_large")

}