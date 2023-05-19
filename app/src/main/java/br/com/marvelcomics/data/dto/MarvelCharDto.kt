package br.com.marvelcomics.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey


data class MarvelCharDto(
    val id: Long?,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail?
)

data class MarvelCharListResponse(
    val results: List<MarvelCharDto>
)

data class Thumbnail(
    val path: String?,
    val extension: String?,
)

@Entity(tableName = "marvel_character")
data class MarvelCharLocal(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: String
)