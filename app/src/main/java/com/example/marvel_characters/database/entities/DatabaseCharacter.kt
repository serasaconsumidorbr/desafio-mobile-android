package com.example.marvel_characters.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvel_characters.domain.MarvelCharacter

@Entity(tableName = "MarvelCharacter")
data class DatabaseCharacter constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val thumbnailUrl: String
) {
    fun asDomainModel() = MarvelCharacter(
        id,
        name,
        description,
        thumbnailUrl
    )
}

fun List<DatabaseCharacter>.asDomainModel(): List<MarvelCharacter> {
    return map {
        MarvelCharacter(
            it.id,
            it.name,
            it.description,
            it.thumbnailUrl
        )
    }
}
