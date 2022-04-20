package com.example.home_data.remote.mapper.impl
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class CharacterSafeStringTest {
    private val mapper = CharacterSafeString()

    companion object {
        private const val SAFE_STRING = "safe string"
    }

    @Test
    fun mapper_emptyString_returnsSafeString() = assertThat(
        mapper.mapFrom(
            stringToMap = "",
            safeValue = SAFE_STRING
        )
    ).isEqualTo(SAFE_STRING)

    @Test
    fun mapper_blankString_returnsSafeString() = assertThat(
        mapper.mapFrom(
            stringToMap = " ",
            safeValue = SAFE_STRING
        )
    ).isEqualTo(SAFE_STRING)

    @Test
    fun mapper_normalString_returnsNormalString() {
        val stringToMap = "string to map"
        assertThat(
            mapper.mapFrom(
                stringToMap = stringToMap,
                safeValue = SAFE_STRING
            )
        ).isEqualTo(stringToMap)
    }
}