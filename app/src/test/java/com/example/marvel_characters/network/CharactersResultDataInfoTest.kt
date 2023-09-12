package com.example.marvel_characters.network

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Test

class CharactersResultDataInfoTest {
    @Test
    fun shouldReturnHasNextTrue() {
        val charactersResultDataInfo = CharactersResultDataInfo(0, 20, 21)
        assertThat(charactersResultDataInfo.hasNextPage(), `is`(true))
    }

    @Test
    fun shouldReturnHasNextFalse() {
        val charactersResultDataInfo = CharactersResultDataInfo(0, 20, 20)
        assertThat(charactersResultDataInfo.hasNextPage(), `is`(false))
    }

    @Test
    fun shouldReturnNextPageOffset() {
        val charactersResultDataInfo = CharactersResultDataInfo(0, 20, 21)
        assertThat(charactersResultDataInfo.nextPageOffset(), `is`(20))
    }

}