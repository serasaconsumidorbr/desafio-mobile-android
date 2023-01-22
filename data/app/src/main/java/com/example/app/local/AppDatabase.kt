package com.example.app.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.heroes.local.dao.HeroDao
import com.example.data.heroes.local.entity.HeroEntity
import com.example.data.heroes.local.entity.Thumbnail
import com.google.gson.Gson

private const val DATABASE_NAME = "app_database.db"
private const val DATABASE_VERSION = 1

@Database(entities = [HeroEntity::class], version = DATABASE_VERSION)
@TypeConverters(Converters::class)
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

private class Converters {
    fun thumbnailtoJson(value: Thumbnail): String = Gson().toJson(value)

    fun jsonToThumbnail(value: String) = Gson().fromJson(value, Thumbnail::class.java)
}