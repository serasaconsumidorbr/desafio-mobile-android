package com.example.home_data.remote.datasource.offset

import com.example.home_data.remote.configs.HomePageConfig
import com.google.common.truth.Truth
import org.junit.Test

internal class OffsetCalculatorImplTest {
    private val configs = HomePageConfig(
        size = 20,
        startingIndex = 3,
        incrementValue = 1
    )
    private val calculator = OffsetCalculatorImpl(configs)

    @Test
    fun calculator_currentIndexLessThanStartIndex_returnsStartIndex() = Truth.assertThat(
        calculator(configs.startingIndex - 1)
    ).isEqualTo(configs.startingIndex)

    @Test
    fun calculator_currentIndexEqualsToStartIndex_returnsStartIndex() = Truth.assertThat(
        calculator(configs.startingIndex)
    ).isEqualTo(configs.startingIndex)

    @Test
    fun calculator_currentIndexBiggerThanStartIndex_returnsOperation() {
        val currentKey = configs.startingIndex + 4
        Truth.assertThat(
            calculator(currentKey)
        ).isEqualTo((currentKey - configs.startingIndex) * configs.size + configs.startingIndex)
    }
}