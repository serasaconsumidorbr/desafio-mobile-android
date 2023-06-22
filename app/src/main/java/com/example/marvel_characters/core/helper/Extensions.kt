package com.example.marvel_characters.core

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.networkLoad(
    url: String,
    @DrawableRes placeholder: Int,
) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}