package com.victorvgc.mymarvelheros.home.ui.heroes_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.victorvgc.mymarvelheros.R
import com.victorvgc.mymarvelheros.databinding.ItemHeroBinding
import com.victorvgc.mymarvelheros.home.domain.domain.Hero

class HeroesAdapter(heroComparator: DiffUtil.ItemCallback<Hero>) :
    PagingDataAdapter<Hero, HeroViewHolder>(heroComparator) {

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder =
        DataBindingUtil.inflate<ItemHeroBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_hero,
            parent,
            false
        ).let { HeroViewHolder(it) }

}