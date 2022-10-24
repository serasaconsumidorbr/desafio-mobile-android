package com.example.marvel_characters.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val imagePath: String,
    val imageExtension: String,
)