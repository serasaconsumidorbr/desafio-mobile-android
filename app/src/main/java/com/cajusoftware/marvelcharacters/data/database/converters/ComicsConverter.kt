package com.cajusoftware.marvelcharacters.data.database.converters

import androidx.room.TypeConverter
import com.cajusoftware.marvelcharacters.data.database.dtos.ComicSummaryDto
import com.cajusoftware.marvelcharacters.data.database.dtos.ComicsDto
import com.cajusoftware.marvelcharacters.utils.toComicSummary
import com.cajusoftware.marvelcharacters.utils.toComicsDto

class ComicsConverter : BaseConverter() {

    @TypeConverter
    fun toComicSummaryDtoList(values: String): List<ComicSummaryDto> {
        val array = values.split(separator)
        val pricesList = mutableListOf<ComicSummaryDto>()
        array.forEach {
            pricesList.add(it.toComicSummary())
        }
        return pricesList
    }

    @TypeConverter
    fun toComicSummaryDtoString(values: List<ComicSummaryDto>): String {
        return values.joinToString(separator)
    }

    @TypeConverter
    fun toComicsDto(values: String): ComicsDto {
        return values.toComicsDto()
    }

    @TypeConverter
    fun toComicsDtoString(values: ComicsDto): String {
        return values.toDataString()
    }
}