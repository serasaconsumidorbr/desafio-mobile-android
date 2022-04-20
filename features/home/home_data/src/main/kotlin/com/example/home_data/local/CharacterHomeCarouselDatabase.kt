package com.example.home_data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterHomeEntity::class],
    version = 1
)
abstract class CharacterHomeCarouselDatabase: RoomDatabase() {
    abstract val dao: CharacterHomeCarouselDao
}