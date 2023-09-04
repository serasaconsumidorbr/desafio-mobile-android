package com.example.marvel_app.framework.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

class GlideImageLoader @Inject constructor(): ImageLoader {
    override fun load(
        imageView: ImageView,
        imageUrl: String,
        placeholder: Int,
        fallback: Int) {
        Glide.with(imageView.rootView)
            .load(imageUrl)
            .placeholder(placeholder)
            .fallback(fallback)
            .into(imageView)
    }
}