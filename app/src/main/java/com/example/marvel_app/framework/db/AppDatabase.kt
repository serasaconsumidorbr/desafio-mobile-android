package com.example.marvel_app.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvel_app.framework.db.dao.CharacterDao
import com.example.marvel_app.framework.db.dao.FavoriteDao
import com.example.marvel_app.framework.db.dao.RemoteKeyDao
import com.example.marvel_app.framework.db.entity.CharacterEntity
import com.example.marvel_app.framework.db.entity.FavoriteEntity
import com.example.marvel_app.framework.db.entity.RemoteKey

@Database(entities = [
    FavoriteEntity::class,
    CharacterEntity::class,
    RemoteKey::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    abstract fun characterDao(): CharacterDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}