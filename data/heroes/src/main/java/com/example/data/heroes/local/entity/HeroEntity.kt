package com.example.data.heroes.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hero")
data class HeroEntity (
    @PrimaryKey(autoGenerate = true)
    val key : Long = 0,
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
)

data class Thumbnail (
    val path: String,
    val extension: String
)

