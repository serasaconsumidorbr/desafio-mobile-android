package com.cajusoftware.marvelcharacters.ui.adapters

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.cajusoftware.marvelcharacters.R
import com.cajusoftware.marvelcharacters.data.domain.CarouselCharacter
import com.cajusoftware.marvelcharacters.data.network.ConnectivityStatus
import com.cajusoftware.marvelcharacters.ui.components.CarouselView
import com.cajusoftware.marvelcharacters.utils.getComicLink
import com.cajusoftware.marvelcharacters.utils.gone
import com.cajusoftware.marvelcharacters.utils.toSafeUri
import com.cajusoftware.marvelcharacters.utils.visible
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@BindingAdapter("items")
fun bindImages(carousel: CarouselView, items: List<CarouselCharacter>?) {
    carousel.items = items
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("items", "scope")
fun bindImages(
    recyclerView: RecyclerView,
    items: PagingData<Nothing>?,
    scope: CoroutineScope?
) {
    (recyclerView.adapter as? PagingDataAdapter<Nothing, *>)?.apply {
        scope?.launch {
            items?.let { this@apply.submitData(it) }
        }
    }
}

@BindingAdapter("visibility")
fun setVisibility(view: View, shouldBeVisible: Boolean) {
    if (shouldBeVisible) view.visible() else view.gone()
}

@BindingAdapter("isNoConnection")
fun showConnectionStatus(textView: TextView, connectStatus: ConnectivityStatus?) {
    val status = connectStatus ?: return

    textView.apply {
        when (status) {
            ConnectivityStatus.OFFLINE -> {
                backgroundTintList = ColorStateList.valueOf(Color.RED)
                text = context.getString(R.string.no_connection_title)
                visible()
            }
            else -> {
                backgroundTintList = ColorStateList.valueOf(Color.parseColor("#228B22"))
                text = context.getString(R.string.back_online)
                Handler(Looper.getMainLooper()).postDelayed({ gone() }, 2000)
            }
        }
    }
}

@BindingAdapter("src")
fun setImage(imageView: ShapeableImageView, uri: Uri?) {
    imageView.load(uri) {
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
        memoryCachePolicy(CachePolicy.ENABLED)
        diskCachePolicy(CachePolicy.ENABLED)
    }
}

@BindingAdapter("description")
fun setTextDescription(
    textView: TextView,
    character: com.cajusoftware.marvelcharacters.data.domain.Character?
) {
    if (character?.description?.isEmpty() == true) {
        val spannableString =
            SpannableString(textView.context.getString(R.string.external_link_description))
        val clickableTerms: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                Intent(Intent.ACTION_VIEW).apply {
                    this.data = character.urls?.getComicLink()?.url?.toSafeUri()
                }.also {
                    textView.context.startActivity(it)
                }
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
            }
        }

        spannableString.setSpan(
            clickableTerms,
            spannableString.length - 4,
            spannableString.length,
            SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.highlightColor = Color.TRANSPARENT
        textView.layoutParams?.apply { this.width = ViewGroup.LayoutParams.WRAP_CONTENT }
            .also { textView.layoutParams = it }
    } else {
        textView.text = character?.description
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
        textView.layoutParams?.apply { this.width = 0 }.also { textView.layoutParams = it }
    }
}

@BindingAdapter("drawableEnd")
fun setEndDrawable(textView: TextView, drawable: Drawable) {
    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
        null,
        null,
        drawable,
        null
    )
    textView.compoundDrawablePadding = 10
}