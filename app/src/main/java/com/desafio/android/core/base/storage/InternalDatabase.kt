package com.desafio.android.core.base.storage

import androidx.room.*
import com.desafio.android.core.standalone.fromObject
import com.desafio.android.core.standalone.fromObjectList
import com.desafio.android.core.standalone.toObject
import com.desafio.android.core.standalone.toObjectList
import com.desafio.android.domain.entity.*
import com.desafio.android.repository.home.sources.MarvelCharacterDao
import com.google.gson.Gson
import java.util.*


@Database(entities = [MarvelCharacter::class], version = 1)
@TypeConverters(Converters::class)
abstract class InternalDatabase : RoomDatabase() {
    abstract fun marvelCharacterDao(): MarvelCharacterDao
}

object Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toMarvelCharacterUrlList(string: String?): List<MarvelCharacterUrl?>? {
        return toObjectList(string)
    }

    @TypeConverter
    fun fromMarvelCharacterUrlList(list: List<MarvelCharacterUrl?>?): String? {
        return fromObjectList(list)
    }

    @TypeConverter
    fun toMarvelCharacterThumbnail(string: String?): MarvelCharacterThumbnail? {
        return Gson().fromJson(string, MarvelCharacterThumbnail::class.java)
    }

    @TypeConverter
    fun fromMarvelCharacterThumbnail(thumbnail: MarvelCharacterThumbnail?): String? {
        return fromObject(thumbnail)
    }

    @TypeConverter
    fun toMarvelCharacterMediaComic(string: String?): MarvelCharacterMedia<MarvelCharacterMediaItem.Comic>? {
        return toObject(string)
    }

    @TypeConverter
    fun fromMarvelCharacterMediaComic(media: MarvelCharacterMedia<MarvelCharacterMediaItem.Comic>?): String? {
        return fromObject(media)
    }

    @TypeConverter
    fun toMarvelCharacterMediaStory(string: String?): MarvelCharacterMedia<MarvelCharacterMediaItem.Story>? {
        return toObject(string)
    }

    @TypeConverter
    fun fromMarvelCharacterMediaStory(media: MarvelCharacterMedia<MarvelCharacterMediaItem.Story>?): String? {
        return fromObject(media)
    }

    @TypeConverter
    fun toMarvelCharacterMediaEvent(string: String?): MarvelCharacterMedia<MarvelCharacterMediaItem.Event>? {
        return toObject(string)
    }

    @TypeConverter
    fun fromMarvelCharacterMediaEvent(media: MarvelCharacterMedia<MarvelCharacterMediaItem.Event>?): String? {
        return fromObject(media)
    }

    @TypeConverter
    fun toMarvelCharacterMediaSeries(string: String?): MarvelCharacterMedia<MarvelCharacterMediaItem.Series>? {
        return toObject(string)
    }

    @TypeConverter
    fun fromMarvelCharacterMediaSeries(media: MarvelCharacterMedia<MarvelCharacterMediaItem.Series>?): String? {
        return fromObject(media)
    }
}