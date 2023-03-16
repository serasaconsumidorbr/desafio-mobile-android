package com.cajusoftware.marvelcharacters.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.helper.widget.Carousel
import coil.load
import coil.request.CachePolicy
import com.cajusoftware.marvelcharacters.R
import com.cajusoftware.marvelcharacters.data.domain.CarouselCharacter
import com.cajusoftware.marvelcharacters.databinding.ViewCarouselBinding
import com.google.android.material.imageview.ShapeableImageView

class CarouselView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val binding = ViewCarouselBinding.inflate(LayoutInflater.from(context), this, true)

    var items: List<CarouselCharacter>? = null
        set(value) {
            field = value
            binding.shouldBeVisible = value.isNullOrEmpty().not()
            if (value.isNullOrEmpty().not())
                setCarousel()
        }

    var onItemClickListener: ((Int) -> Unit)? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CarouselView)
        typedArray.recycle()
    }

    private fun setCarousel() {
        binding.apply {
            carousel.apply {
                setAdapter(object : Carousel.Adapter {
                    override fun count(): Int {
                        return items?.size ?: 0
                    }

                    override fun populate(view: View, index: Int) {
                        (view as? ShapeableImageView)?.load(items?.get(index)?.thumbnail?.thumbnailUri) {
                            placeholder(R.drawable.loading_animation)
                            error(R.drawable.ic_broken_image)
                            memoryCachePolicy(CachePolicy.ENABLED)
                            diskCachePolicy(CachePolicy.ENABLED)
                        }

                        characterName.text = items?.get(index)?.name
                        characterDescription.text = items?.get(index)?.copyright

                        view.setOnClickListener {

                            if (currentIndex != index) {
                                val lastItem = carousel.count - 1
                                when (currentIndex) {
                                    0 -> if (index == 1)
                                        transitionToIndex(index, 1000) else jumpToIndex(index)
                                    lastItem -> if (index == lastItem - 1)
                                        transitionToIndex(index, 1000) else jumpToIndex(index)
                                    else -> transitionToIndex(index, 1000)
                                }
                            } else
                                onItemClickListener?.invoke(
                                    items?.get(index)?.id ?: throw CarouselItemNotFound()
                                )
                        }
                    }

                    override fun onNewItem(index: Int) {
                    }
                })
            }.also { carousel.refresh() }
        }
    }
}