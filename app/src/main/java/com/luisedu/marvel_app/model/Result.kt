package com.luisedu.marvel_app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("comics") val comics: Comics,
    @SerializedName("series") val series: Series,
    @SerializedName("stories") val stories: Stories,
    @SerializedName("events") val events: Events,


    //Chaves Extras da Chamada '/{characterId}/Comic'
    @SerializedName("digitalId") val digitalId: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("issueNumber") val issueNumber: Int?,
    @SerializedName("variantDescription") val variantDescription: String?,
    @SerializedName("isbn") val isbn: String?,
    @SerializedName("upc") val upc: String?,
    @SerializedName("diamondCode") val diamondCode: String?,
    @SerializedName("ean") val ean: String?,
    @SerializedName("issn") val issn: String?,
    @SerializedName("format") val format: String?,
    @SerializedName("pageCount") val pageCount: Int?,
    @SerializedName("dates") val dates: List<Date>?,
    @SerializedName("creators") val creators: Creators?,
    @SerializedName("characters") val characters: Characters?
) : Parcelable