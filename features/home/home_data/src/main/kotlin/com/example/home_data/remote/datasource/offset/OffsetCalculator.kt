package com.example.home_data.remote.datasource.offset

interface OffsetCalculator {
    operator fun invoke(
        currentKey: Int
    ): Int
}