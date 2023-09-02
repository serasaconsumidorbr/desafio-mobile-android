package com.example.marvel_app.presentation.character.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.core.features.characters.domain.model.Character
import com.example.marvel_app.framework.imageloader.ImageLoader
import com.example.marvel_app.presentation.character.viewholder.CharactersViewHolder
import javax.inject.Inject

class CharacterAdapter @Inject constructor(
    private val imageLoader: ImageLoader
): PagingDataAdapter<Character, CharactersViewHolder>(diffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder.create(parent,imageLoader)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
       getItem(position)?.let {
           holder.bind(it)
       }
    }

    companion object {
        private val diffUtils = object: DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(
                oldItem: Character,
                newItem: Character)
            : Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}