package com.example.marvel_characters.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvel_characters.domain.models.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class MarvelRoomDatabase: RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

    companion object {

        @Volatile
        private var INSTANCE: MarvelRoomDatabase? = null

        fun getMarvelRoomDatabase(context: Context): MarvelRoomDatabase {
            val tempInstace = INSTANCE
            if (tempInstace != null) {
                return tempInstace
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarvelRoomDatabase::class.java,
                    "characters_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}