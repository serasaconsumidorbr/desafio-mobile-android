package com.example.app.local

import android.app.Application
import androidx.room.*
import com.example.data.heroes.local.dao.HeroDao
import com.example.data.heroes.local.entity.HeroEntity
import com.example.data.heroes.local.entity.Thumbnail
import com.google.gson.Gson

private const val DATABASE_NAME = "app_database.db"
private const val DATABASE_VERSION = 2

@Database(entities = [HeroEntity::class], version = DATABASE_VERSION)
@TypeConverters(ThumbnailConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao


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


object ThumbnailConverter {
    @TypeConverter
    fun thumbnailtoJson(value: Thumbnail): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToThumbnail(value: String): Thumbnail {
        return Gson().fromJson(value, Thumbnail::class.java)
    }
}