package com.cajusoftware.marvelcharacters.data.database.converters

import androidx.room.TypeConverter
import com.cajusoftware.marvelcharacters.data.database.dtos.EventsDto
import com.cajusoftware.marvelcharacters.data.database.dtos.EventsSummaryDto
import com.cajusoftware.marvelcharacters.utils.toEventsDto
import com.cajusoftware.marvelcharacters.utils.toEventsSummary

class EventsConverter : BaseConverter() {

    @TypeConverter
    fun toEventsSummaryDtoList(values: String): List<EventsSummaryDto> {
        val array = values.split(separator)
        val pricesList = mutableListOf<EventsSummaryDto>()
        array.forEach {
            pricesList.add(it.toEventsSummary())
        }
        return pricesList
    }

    @TypeConverter
    fun toEventsSummaryDtoString(values: List<EventsSummaryDto>): String {
        return values.joinToString(separator)
    }

    @TypeConverter
    fun toEventsDto(values: String): EventsDto {
        return values.toEventsDto()
    }

    @TypeConverter
    fun toEventsDtoString(values: EventsDto): String {
        return values.toDataString()
    }
}