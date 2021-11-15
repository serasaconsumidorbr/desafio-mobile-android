package br.com.maceda.marvel.data.dao

import androidx.room.TypeConverter
import br.com.maceda.marvel.data.model.Thumbnail
import com.google.gson.Gson

class ThumbnailConverter {

    @TypeConverter
    fun stringToThumbnail(value: String) = Gson().fromJson(value, Thumbnail::class.java)

    @TypeConverter
    fun thumbnailToString(thumbnail: Thumbnail) = Gson().toJson(thumbnail)
}
