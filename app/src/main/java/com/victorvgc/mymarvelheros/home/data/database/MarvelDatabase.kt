package com.victorvgc.mymarvelheros.home.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.victorvgc.mymarvelheros.home.data.model.local.LocalHero

@Database(entities = [LocalHero::class], version = 1, exportSchema = false)
abstract class MarvelDatabase : RoomDatabase() {
    companion object {
        const val name = "marvelDb"
    }

    abstract fun heroDao(): HeroDao
}