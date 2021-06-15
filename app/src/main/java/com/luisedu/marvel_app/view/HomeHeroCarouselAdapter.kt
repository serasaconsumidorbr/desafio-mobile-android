package com.luisedu.marvel_app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.islamkhsh.CardSliderAdapter
import com.luisedu.marvel_app.R
import com.luisedu.marvel_app.model.Result
import com.luisedu.marvel_app.utils.CharacterOnClickListener

class HomeHeroCarouselAdapter(private val onClickListener: CharacterOnClickListener) : CardSliderAdapter<HomeHeroCarouselAdapter.ViewHolder>() {

    private var charactersList = listOf<Result>()
    private val maxItems = 5

    fun addCharactersList(items: List<Result>) {
        charactersList = items
    }

    override fun getItemCount(): Int {
        return if (charactersList.size > maxItems) {
            maxItems
        } else charactersList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_carousel_hero, parent, false))

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHeroCarousel: TextView = itemView.findViewById(R.id.tvHeroCarousel)
        val ivHeroCarousel: ImageView = itemView.findViewById(R.id.ivHeroCarousel)
    }

    override fun bindVH(holder: ViewHolder, position: Int) {
        charactersList[position].run {
            holder.apply {
                tvHeroCarousel.text = name

                Glide.with(itemView)
                    .load(thumbnail.path + "/standard_fantastic." + thumbnail.extension)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(ivHeroCarousel)

                itemView.setOnClickListener {
                    onClickListener.onClickCharacter(charactersList[position])
                }

                ivHeroCarousel.contentDescription = "Imagem do personagem $name"
            }
        }
    }
}