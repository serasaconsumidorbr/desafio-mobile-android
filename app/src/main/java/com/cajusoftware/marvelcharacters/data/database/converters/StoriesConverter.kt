package com.cajusoftware.marvelcharacters.data.database.converters

import androidx.room.TypeConverter
import com.cajusoftware.marvelcharacters.data.database.dtos.StoriesDto
import com.cajusoftware.marvelcharacters.data.database.dtos.StoriesSummaryDto
import com.cajusoftware.marvelcharacters.utils.toStoriesDto
import com.cajusoftware.marvelcharacters.utils.toStoriesSummary

class StoriesConverter : BaseConverter() {

    @TypeConverter
    fun toStoriesSummaryDtoList(values: String): List<StoriesSummaryDto> {
        val array = values.split(separator)
        val pricesList = mutableListOf<StoriesSummaryDto>()
        array.forEach {
            pricesList.add(it.toStoriesSummary())
        }
        return pricesList
    }

    @TypeConverter
    fun toStoriesSummaryDtoString(values: List<StoriesSummaryDto>): String {
        return values.joinToString(separator)
    }

    @TypeConverter
    fun toSeriesDto(values: String): StoriesDto {
        return values.toStoriesDto()
    }

    @TypeConverter
    fun toSeriesString(values: StoriesDto): String {
        return values.toDataString()
    }
}