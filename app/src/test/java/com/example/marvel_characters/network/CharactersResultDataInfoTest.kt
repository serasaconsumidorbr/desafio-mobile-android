package com.example.marvel_characters.network

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test

class CharactersResultDataInfoTest {
    @Test
    fun shouldReturnHasNextTrue() {
        val charactersResultDataInfo = CharactersResultDataInfo(0, 20, 21)
        Assert.assertTrue(charactersResultDataInfo.hasNextPage())
    }

    @Test
    fun shouldReturnHasNextFalse() {
        val charactersResultDataInfo = CharactersResultDataInfo(0, 20, 20)
        Assert.assertFalse(charactersResultDataInfo.hasNextPage())

    }

    @Test
    fun shouldReturnNextPageOffset() {
        val charactersResultDataInfo = CharactersResultDataInfo(0, 20, 21)
        MatcherAssert.assertThat(
            charactersResultDataInfo.nextPageOffset(),
            CoreMatchers.equalTo(20)
        )

    }

}