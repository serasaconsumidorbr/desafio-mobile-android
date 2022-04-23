package com.br.leandro.marvel_hero_app.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.br.leandro.marvel_hero_app.R

fun ImageView.load(url: String) {
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(R.drawable.ic_loading)
        .into(this)
}

fun ImageView.loadCircleCrop(url: String) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(R.drawable.ic_loading)
        .into(this)
}