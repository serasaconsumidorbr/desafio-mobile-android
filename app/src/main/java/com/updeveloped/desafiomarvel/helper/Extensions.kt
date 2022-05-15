package com.updeveloped.desafiomarvel.helper

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.updeveloped.desafiomarvel.R
import java.math.BigInteger
import java.security.MessageDigest

fun String.getMd5Digest(): String {
    val md = MessageDigest.getInstance("MD5")
    val byteArray = this.toByteArray()
    val bigInt = BigInteger(1, md.digest(byteArray))
    return bigInt.toString(16).padStart(32, '0')
}

fun ImageView.loadGlideUrl(
    url: String,
) {
    val secureUrl = url.replace("http", "https")
    Glide.with(context)
        .load(secureUrl)
        .placeholder(circularProgress(context))
        .into(this)
}

fun circularProgress(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.setColorSchemeColors(ContextCompat.getColor(context, R.color.red))
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    return circularProgressDrawable
}