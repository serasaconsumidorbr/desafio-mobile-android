package com.cajusoftware.marvelcharacters.data.database.converters

import androidx.room.TypeConverter
import com.cajusoftware.marvelcharacters.data.database.dtos.UrlsDto
import com.cajusoftware.marvelcharacters.utils.toUrlsDto

class UrlsConverter : BaseConverter() {

    @TypeConverter
    fun toUrlsDtoList(values: String): List<UrlsDto> {
        val array = values.split(separator)
        val pricesList = mutableListOf<UrlsDto>()
        array.forEach {
            pricesList.add(it.toUrlsDto())
        }
        return pricesList
    }

    @TypeConverter
    fun toUrlsDtoString(values: List<UrlsDto>): String {
        return values.joinToString(separator)
    }

    @TypeConverter
    fun toUrlsDto(values: String): UrlsDto {
        return values.toUrlsDto()
    }

    @TypeConverter
    fun toUrlsDtoString(values: UrlsDto): String {
        return values.toDataString()
    }
}