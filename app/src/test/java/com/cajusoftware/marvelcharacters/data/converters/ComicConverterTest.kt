package com.cajusoftware.marvelcharacters.data.converters

import com.cajusoftware.marvelcharacters.data.database.converters.ComicsConverter
import com.cajusoftware.tests.fakes.FakeData.comicsDto
import com.cajusoftware.tests.fakes.FakeData.comicsDtoString
import org.junit.Test
import kotlin.test.assertEquals

class ComicsConverterTest {

    private val comicsConverter = ComicsConverter()

    @Test
    fun comicConverterTest_toComicsDto_verifyComicDto() {
        assertEquals(comicsDto, comicsConverter.toComicsDto(comicsDtoString))
    }

    @Test
    fun comicConverterTest_toComicsDtoString_verifyComicDto() {
        assertEquals(comicsDtoString, comicsConverter.toComicsDtoString(comicsDto))
    }
}