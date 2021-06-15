package com.luisedu.marvel_app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.luisedu.marvel_app.R
import com.luisedu.marvel_app.model.Result
import com.luisedu.marvel_app.utils.CharacterOnClickListener

class HomeHeroListAdapter(private val onClickListener: CharacterOnClickListener) : RecyclerView.Adapter<HomeHeroListAdapter.ViewHolder>() {

    private var charactersList = listOf<Result>()
    private var carouselSize = 5

    fun addCharactersList(items: List<Result>) {
        charactersList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_character_info_item, parent, false))

    override fun getItemCount() = charactersList.size - carouselSize

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = charactersList[position + carouselSize]

        holder.apply {
            tvCharactersNameHomeList.text = items.name
            tvCharactersDescriptionHomeList.text = items.description

            Glide.with(itemView)
                .load(items.thumbnail.path + "/portrait_incredible." + items.thumbnail.extension)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(ivCharacterImageHomeList)

            itemView.setOnClickListener {
                onClickListener.onClickCharacter(items)
            }

            tvCharactersNameHomeList.contentDescription = items.name
            tvCharactersDescriptionHomeList.contentDescription =
                "Descrição do personagem ${tvCharactersNameHomeList.text}, ${tvCharactersDescriptionHomeList.text}"
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCharactersNameHomeList: TextView = itemView.findViewById(R.id.tvHero)
        val tvCharactersDescriptionHomeList: TextView = itemView.findViewById(R.id.tvHeroDescription)
        val ivCharacterImageHomeList: ImageView = itemView.findViewById(R.id.ivHero)
    }
}