package com.luisedu.marvel_app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
    @SerializedName("available") val available: Int?,
    @SerializedName("collectionURI") val collectionURI: String?,
    @SerializedName("items") val items: List<Item>?,
    @SerializedName("returned") val returned: Int?,

    //Chaves Extras da Chamada '/{characterId}/Comic'
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("name") val name: String?
) : Parcelable