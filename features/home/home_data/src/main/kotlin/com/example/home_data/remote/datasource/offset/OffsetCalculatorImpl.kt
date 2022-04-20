package com.example.home_data.remote.datasource.offset

import com.example.home_data.remote.configs.HomePageConfig
import javax.inject.Inject

class OffsetCalculatorImpl @Inject constructor(
    private val homePageConfig: HomePageConfig,
) : OffsetCalculator {
    override operator fun invoke(currentKey: Int): Int = with(homePageConfig) {
        if (currentKey <= startingIndex) {
            startingIndex
        } else {
            (currentKey - startingIndex) * size
        }
    }
}