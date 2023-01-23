package com.example.feature.hero_list.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.imageLoader
import coil.request.ImageRequest

class ImageCoil {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String) {
            if (url.isNotEmpty()) {
                val imageLoader = view.context.imageLoader

                val request = ImageRequest.Builder(view.context)
                    .data(url)
                    .crossfade(true)
                    .target(view)
                    .build()
                imageLoader.enqueue(request)
            }

        }
    }

}