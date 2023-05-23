package com.example.testeapp.data

import androidx.room.TypeConverter
import com.example.testeapp.model.Image
import com.example.testeapp.model.ResourceItem
import com.example.testeapp.model.ResourceList
import com.example.testeapp.model.Url
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun toTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromImageString(value: String): Image {
        return gson.fromJson(value, Image::class.java)
    }

    @TypeConverter
    fun toImageString(image: Image): String {
        return gson.toJson(image)
    }

    @TypeConverter
    fun fromUrlListString(value: String): List<Url> {
        val listType = object : TypeToken<List<Url>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toUrlListString(urlList: List<Url>): String {
        return gson.toJson(urlList)
    }

    @TypeConverter
    fun fromUrlString(value: String): Url {
        return gson.fromJson(value, Url::class.java)
    }

    @TypeConverter
    fun toUrlString(url: Url): String {
        return gson.toJson(url)
    }

    @TypeConverter
    fun fromResourceItemString(value: String): ResourceItem {
        return gson.fromJson(value, ResourceItem::class.java)
    }

    @TypeConverter
    fun toResourceItemString(resourceItem: ResourceItem): String {
        return gson.toJson(resourceItem)
    }

    @TypeConverter
    fun fromResourceListString(value: String): ResourceList {
        return gson.fromJson(value, ResourceList::class.java)
    }

    @TypeConverter
    fun toResourceListString(resourceList: ResourceList): String {
        return gson.toJson(resourceList)
    }
}