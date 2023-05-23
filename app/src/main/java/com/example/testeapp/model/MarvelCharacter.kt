package com.example.testeapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

data class ListResponse(
    @SerializedName("data")
    val data: DataMarvel
)

data class DataMarvel(
    @SerializedName("results")
    val result: List<MarvelCharacter>
)

@Parcelize
@Entity(tableName = "characters")
data class MarvelCharacter(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: List<Url>,
    val thumbnail: Image,
    val comics: ResourceList,
    val stories: ResourceList,
    val events: ResourceList,
    val series: ResourceList,
    var isFavorite: Boolean = false
): Parcelable
@Parcelize
data class Url(
    val type: String,
    val url: String
): Parcelable
@Parcelize
data class Image(
    val path: String,
    val extension: String
): Parcelable
@Parcelize
data class ResourceList(
    val available: Int,
    val collectionURI: String,
    val items: List<ResourceItem>,
    val returned: Int
): Parcelable
@Parcelize
data class ResourceItem(
    val resourceURI: String,
    val name: String
): Parcelable
