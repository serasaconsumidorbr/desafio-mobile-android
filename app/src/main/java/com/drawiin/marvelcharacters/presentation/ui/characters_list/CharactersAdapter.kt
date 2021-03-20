package com.drawiin.marvelcharacters.presentation.ui.characters_list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.drawiin.marvelcharacters.domain.model.character.Character

class CharactersAdapter : ListAdapter<Character, CharacterItemViewHolder>(DiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        return CharacterItemViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
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