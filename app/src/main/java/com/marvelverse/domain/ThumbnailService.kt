package com.marvelverse.domain

import android.widget.ImageView

interface ThumbnailService {

    fun loadPortraitThumbnail(thumbnail: ThumbnailImage, imageView: ImageView)

    fun loadLandscapeThumbnail(thumbnail: ThumbnailImage, imageView: ImageView)
}