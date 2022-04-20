package com.example.home_data.factories

import com.example.home_data.remote.dto.ThumbnailDto

object ThumbnailDtoFactory {
    fun make(
        path: String? = "path",
        extension: String? = ".jpg"
    ): ThumbnailDto = ThumbnailDto(
        path = path,
        extension = extension
    )
}