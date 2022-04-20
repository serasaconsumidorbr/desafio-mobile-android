package com.br.leandro.marvel_hero_app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Hero::class],
    version = 1
)
abstract class SaveMyHeroDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao

    companion object {
        private const val DB_NAME = "savemyhero_database"

        @Volatile
        private var INSTANCE: SaveMyHeroDatabase? = null

        fun getInstance(context: Context): SaveMyHeroDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SaveMyHeroDatabase::class.java,
                    DB_NAME
                )
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}