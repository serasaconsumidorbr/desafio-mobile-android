package com.example.marvel_app.features.detail.args

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DetailViewArg(
    val name: String,
    val imageUrl: String
): Parcelable
