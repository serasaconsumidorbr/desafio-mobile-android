package com.example.marvel_characters.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvel_characters.domain.Character

@Entity(tableName = "Character")
data class DatabaseCharacter constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val thumbnailUrl: String
) {
    fun asDomainModel() = Character(
        id,
        name,
        description,
        thumbnailUrl
    )
}

fun List<DatabaseCharacter>.asDomainModel(): List<Character> {
    return map {
        Character(
            it.id,
            it.name,
            it.description,
            it.thumbnailUrl
        )
    }
}
