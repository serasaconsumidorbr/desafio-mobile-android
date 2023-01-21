package com.example.app.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.heroes.local.dao.HeroDao
import com.example.data.heroes.local.entity.HeroEntity

private const val DATABASE_NAME = "app_database.db"
private const val DATABASE_VERSION = 1

@Database(entities = [HeroEntity::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase(){

    abstract fun heroDao() : HeroDao


    class Builder(private val application: Application) {
        private val builder: RoomDatabase.Builder<AppDatabase>
            get() = Room.databaseBuilder(
                application.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )

        fun build(): AppDatabase = builder.build()

    }
}