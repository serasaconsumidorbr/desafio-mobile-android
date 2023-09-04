package com.example.marvel_app.framework.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.example.marvel_app.R

interface ImageLoader {

    fun load(
        imageView: ImageView,
        imageUrl: String,
        @DrawableRes placeholder: Int = R.drawable.ic_image_placeholder,
        @DrawableRes fallback: Int = R.drawable.ic_img_loading_error)
}