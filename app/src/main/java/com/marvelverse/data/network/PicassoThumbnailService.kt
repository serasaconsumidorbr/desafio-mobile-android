package com.marvelverse.data.network

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.marvelverse.R
import com.marvelverse.domain.ThumbnailImage
import com.marvelverse.domain.ThumbnailService

class PicassoThumbnailService : ThumbnailService {
    override fun loadPortraitThumbnail(thumbnail: ThumbnailImage, imageView: ImageView) {
        val portraitThumbnail = "${thumbnail.url}/portrait_incredible.${thumbnail.extension}"
        Picasso.get().load(portraitThumbnail).error(R.drawable.marvel)
            .into(imageView)
    }

    override fun loadLandscapeThumbnail(thumbnail: ThumbnailImage, imageView: ImageView) {
        val portraitThumbnail = "${thumbnail.url}/landscape_incredible.${thumbnail.extension}"
        Picasso.get().load(portraitThumbnail).error(R.drawable.marvel)
            .into(imageView)
    }
}