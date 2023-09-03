package com.example.marvel_app.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvel_app.framework.db.dao.FavoriteDao
import com.example.marvel_app.framework.db.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}