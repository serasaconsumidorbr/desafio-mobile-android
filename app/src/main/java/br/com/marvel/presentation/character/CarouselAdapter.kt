package br.com.marvel.presentation.character

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.helper.widget.Carousel
import br.com.marvel.domain.models.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class CarouselAdapter(private val numImages: Int, private val characters: List<Character?>) :
    Carousel.Adapter {
    override fun count(): Int {
        return numImages;
    }

    override fun populate(view: View?, index: Int) {
        if (view is ImageView) {
            Glide.with(view)
                .load("${characters[index]?.thumbnail?.path}.${characters[index]?.thumbnail?.extension}")
                .centerCrop().error(android.R.drawable.stat_notify_error)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view)
        }
    }

    override fun onNewItem(index: Int) {
    }
}