package com.cajusoftware.marvelcharacters.data.database.converters

import androidx.room.TypeConverter
import com.cajusoftware.marvelcharacters.data.database.dtos.ThumbnailDto
import com.cajusoftware.marvelcharacters.utils.toThumbnailDto

class ThumbnailConverter : BaseConverter() {
    @TypeConverter
    fun toThumbnailDto(values: String): ThumbnailDto {
        return values.toThumbnailDto()
    }

    @TypeConverter
    fun toThumbnailDtoString(values: ThumbnailDto): String {
        return values.toDataString()
    }
}