package com.example.testeapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testeapp.model.MarvelCharacter

@Database(entities = [MarvelCharacter::class], version = 1)
@TypeConverters(Converters::class)
abstract class CharacterRoom : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {

        private const val DB_NAME = "Character-db"

        // For Singleton instantiation
        @Volatile
        private var instance: CharacterRoom? = null

        fun getInstance(context: Context): CharacterRoom {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CharacterRoom {
            return Room.databaseBuilder(
                context,
                CharacterRoom::class.java, DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }

}