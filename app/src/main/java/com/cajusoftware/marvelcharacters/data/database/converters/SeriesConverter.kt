package com.cajusoftware.marvelcharacters.data.database.converters

import androidx.room.TypeConverter
import com.cajusoftware.marvelcharacters.data.database.dtos.SeriesDto
import com.cajusoftware.marvelcharacters.data.database.dtos.SeriesSummaryDto
import com.cajusoftware.marvelcharacters.utils.toSeriesDto
import com.cajusoftware.marvelcharacters.utils.toSeriesSummary

class SeriesConverter : BaseConverter() {

    @TypeConverter
    fun toSeriesSummaryDtoList(values: String): List<SeriesSummaryDto> {
        val array = values.split(separator)
        val pricesList = mutableListOf<SeriesSummaryDto>()
        array.forEach {
            pricesList.add(it.toSeriesSummary())
        }
        return pricesList
    }

    @TypeConverter
    fun toSeriesSummaryDtoString(values: List<SeriesSummaryDto>): String {
        return values.joinToString(separator)
    }

    @TypeConverter
    fun toSeriesDto(values: String): SeriesDto {
        return values.toSeriesDto()
    }

    @TypeConverter
    fun toSeriesString(values: SeriesDto): String {
        return values.toDataString()
    }
}