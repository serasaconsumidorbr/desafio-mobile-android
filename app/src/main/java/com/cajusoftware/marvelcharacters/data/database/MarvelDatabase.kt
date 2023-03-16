package com.cajusoftware.marvelcharacters.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cajusoftware.marvelcharacters.BuildConfig
import com.cajusoftware.marvelcharacters.data.database.converters.*
import com.cajusoftware.marvelcharacters.data.database.dao.CharacterDao
import com.cajusoftware.marvelcharacters.data.database.dtos.CharacterDto

@Database(
    entities = [CharacterDto::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
@TypeConverters(
    ComicsConverter::class,
    EventsConverter::class,
    SeriesConverter::class,
    StoriesConverter::class,
    ThumbnailConverter::class,
    UrlsConverter::class
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}