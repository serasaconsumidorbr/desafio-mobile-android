package com.cajusoftware.marvelcharacters.utils

import com.cajusoftware.marvelcharacters.data.database.dtos.BaseSummaryDto
import com.cajusoftware.marvelcharacters.data.domain.Urls

fun List<BaseSummaryDto>.toDataString(): String {
    return "[${this.joinToString(String().separator)}]"
}

fun List<Urls>.getComicLink() = this.firstOrNull { it.type == "comiclink" }