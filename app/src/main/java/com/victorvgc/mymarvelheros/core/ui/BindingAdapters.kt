package com.victorvgc.mymarvelheros.core.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("bindImage")
fun ImageView.bindImage(url: String?) {
    url?.let {
        Glide
            .with(this.context)
            .load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)
    }
}