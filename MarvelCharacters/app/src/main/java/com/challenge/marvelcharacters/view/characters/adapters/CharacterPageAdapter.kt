package com.challenge.marvelcharacters.view.characters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.challenge.marvelcharacters.R
import com.challenge.marvelcharacters.databinding.CharactersDetailBinding
import com.challenge.marvelcharacters.model.Character

class CharacterPageAdapter: PagingDataAdapter<Character, CharacterViewHolder>(
    CHARACTER_COMPARATOR
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = DataBindingUtil.inflate<CharactersDetailBinding>(LayoutInflater.from(parent.context), R.layout.characters_detail,parent, false)
        LayoutInflater.from(parent.context).inflate(R.layout.characters_detail,parent, false)
        return CharacterViewHolder(
            view
        )
    }
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position) as Character)
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }
}