package com.victorvgc.mymarvelheros.home.ui.heroes_list

import androidx.recyclerview.widget.RecyclerView
import com.victorvgc.mymarvelheros.databinding.ItemHeroBinding
import com.victorvgc.mymarvelheros.home.domain.domain.Hero

class HeroViewHolder(private val binding: ItemHeroBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(hero: Hero?) {
        binding.apply {
            item = hero
        }
    }
}