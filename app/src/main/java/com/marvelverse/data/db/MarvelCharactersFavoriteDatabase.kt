package com.marvelverse.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [MarvelCharacterEntity::class], version = 1)
abstract class MarvelCharactersFavoriteDatabase : RoomDatabase() {
    abstract fun marvelCharacterFavoritesDao(): MarvelCharacterFavoritesDao

    companion object {
        @Volatile
        private var INSTANCE: MarvelCharactersFavoriteDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope, name: String): RoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarvelCharactersFavoriteDatabase::class.java,
                    name
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
