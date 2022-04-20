package com.br.leandro.marvel_hero_app.ui.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.br.leandro.marvel_hero_app.databinding.HeroItemListBinding
import com.br.leandro.marvel_hero_app.domain.hero.Hero
import com.br.leandro.marvel_hero_app.ui.core.HeroViewHolder
import com.br.leandro.marvel_hero_app.ui.core.OnFavoriteButtonClick
import com.bumptech.glide.RequestManager

class HomeHeroListAdapter (
    private val onFavoriteButtonClick: OnFavoriteButtonClick,
    private val requestManager: RequestManager
) : ListAdapter<Hero, HeroViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Hero>() {
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem == newItem
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            binding = HeroItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            requestManager = requestManager
        )
    }
    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(getItem(position), onFavoriteButtonClick, false)
    }
}