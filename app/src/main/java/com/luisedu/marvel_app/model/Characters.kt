package com.luisedu.marvel_app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Characters(
        @SerializedName("available") val available: Int,
        @SerializedName("collectionURI") val collectionURI: String,
        @SerializedName("items") val items: List<Item>,
        @SerializedName("returned") val returned: Int
) : Parcelable