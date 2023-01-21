package com.example.data.heroes.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hero")
data class HeroEntity (
    @PrimaryKey
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

/*
@TypeConverter
fun campoToJson(value : Campo) : String = Gson().toJson(value)

fun jsonToCampo(value : String) = Gson().fromJson(value, Campo>::class.java)
 */