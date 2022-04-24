package dev.ribeiro.bruno.desafioandroid.presentation.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.helper.widget.Carousel
import com.bumptech.glide.Glide
import dev.ribeiro.bruno.desafioandroid.R
import dev.ribeiro.bruno.desafioandroid.domain.entities.CharacterDetail
import dev.ribeiro.bruno.desafioandroid.domain.entities.Thumbnail

class CarouselAdapter(numImages: Int, listCarousel: List<CharacterDetail>) {
    val adapter = object : Carousel.Adapter {
        override fun count(): Int {
            return numImages
        }

        override fun populate(view: View, index: Int) {
            if (view is ImageView) {
                val characterDetail = listCarousel[index].thumbnail
                view.context?.let { setImage(characterDetail, it, view) }
            }
        }

        override fun onNewItem(index: Int) {}
    }

    private fun setImage(character: Thumbnail?, context: Context, view: ImageView) {
        Glide.with(context)
            .load("${character?.path}.${character?.extension}")
            .error(R.drawable.placeholder_couple_superhero)
            .into(view)
    }
}