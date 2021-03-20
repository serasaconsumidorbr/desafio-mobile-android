package com.drawiin.marvelcharacters.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.drawiin.marvelcharacters.R
import java.math.BigInteger
import java.security.MessageDigest

fun String.getMd5Digest(): String {
    val md = MessageDigest.getInstance("MD5")
    val byteArray = this.toByteArray()
    val bigInt = BigInteger(1, md.digest(byteArray))
    return bigInt.toString(16).padStart(32, '0')
}

fun ImageView.loadFromUrl(
    url: String,
    @DrawableRes
    placeholder: Int = R.drawable.ic_super_woman
) {
    val secureUrl = url.replace("http", "https")

    Glide.with(context)
        .load(secureUrl)
        .placeholder(placeholder)
        .into(this)
}