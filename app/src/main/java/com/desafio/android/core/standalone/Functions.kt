package com.desafio.android.core.standalone

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asAndroidColorFilter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.desafio.android.core.base.BaseApplication
import com.desafio.android.ui.ContainerActivity
import com.google.gson.Gson
import java.math.BigInteger
import java.security.MessageDigest

fun getApplicationContext(): Context {
    return BaseApplication.instance
}

fun getActivity(): ContainerActivity? {
    return BaseApplication.instance.activity as ContainerActivity?
}

fun getString(id: Int): String {
    val resources = getApplicationContext().resources
    return resources.getString(id)
}

fun runOnUiThread(action: () -> Unit) {
    getApplicationContext().run {
        val handler = Handler(Looper.getMainLooper())
        handler.post(action)
    }
}

internal fun getImage(
    builder: RequestBuilder<Drawable>,
    onLoading: ((CircularProgressDrawable) -> Unit),
    onLoaded: ((Drawable) -> Unit),
    color: Color = Color.Black
) {
    Thread {
        val circularProgressDrawable =
            getProgressDrawable(
                context = getApplicationContext(),
                strokeWidth = 5f,
                color = color
            )

        runOnUiThread {
            circularProgressDrawable.start()
        }
        onLoading(circularProgressDrawable)

        builder
            .fitCenter()
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(0.05f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    onLoaded(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }.start()
}

internal fun getImageFromUrl(
    url: String,
    onLoading: ((CircularProgressDrawable) -> Unit),
    onLoaded: ((Drawable) -> Unit),
    color: Color = Color.Black
) {
    getImage(
        builder = Glide
            .with(getApplicationContext())
            .load(url),
        onLoading = onLoading,
        onLoaded = onLoaded,
        color = color
    )
}

internal fun getProgressDrawable(
    context: Context,
    strokeWidth: Float,
    color: Color = Color.Black
): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = strokeWidth
    circularProgressDrawable.centerRadius = 60f
    circularProgressDrawable.colorFilter = ColorFilter.tint(color = color).asAndroidColorFilter()

    return circularProgressDrawable
}

internal inline fun <reified T> toObject(string: String?): T? {
    return if (string == null) null else Gson().fromJson(string, T::class.java)
}

internal inline fun <reified T> fromObject(obj: T?): String? {
    val gson = Gson()
    return gson.toJson(obj)
}

internal inline fun <reified T> toObjectList(string: String?): List<T?>? {
    return toObject(string)
}

internal inline fun <reified T> fromObjectList(list: List<T?>?): String? {
    return fromObject(list)
}

fun getRandomString(length: Int): String {
    val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return List(length) { alphabet.random() }.joinToString("")
}

fun getMD5(input: String): String {
    val messageDigest = MessageDigest.getInstance("MD5")
    return BigInteger(1, messageDigest.digest(input.toByteArray())).toString(16).padStart(32, '0')
}