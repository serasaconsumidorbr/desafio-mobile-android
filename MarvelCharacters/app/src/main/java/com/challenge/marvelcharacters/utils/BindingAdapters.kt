package com.challenge.marvelcharacters.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.challenge.marvelcharacters.R

@BindingAdapter("imageUrl")
 fun bindImage(view : ImageView, imgUrl : String){
    imgUrl.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(view.context)
            .load(imgUri)
            .error(R.drawable.ic_broken_image)
            .into(view)

    }

}