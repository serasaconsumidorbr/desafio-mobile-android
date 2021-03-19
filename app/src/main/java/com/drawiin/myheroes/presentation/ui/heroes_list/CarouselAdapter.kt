package com.drawiin.myheroes.presentation.ui.heroes_list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.drawiin.myheroes.domain.model.character.Character

class CarouselAdapter : ListAdapter<Character, CarouselItemViewHolder>(DiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        return CarouselItemViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    companion object DiffUtilCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

    }
}