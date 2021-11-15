package br.com.maceda.marvel.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.maceda.marvel.data.model.Character

@Database(entities = [Character::class], version = 1)
@TypeConverters(ThumbnailConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
