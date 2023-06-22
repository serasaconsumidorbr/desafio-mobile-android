package com.example.marvel_characters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.marvel_characters.R
import com.example.marvel_characters.core.networkLoad
import com.example.marvel_characters.databinding.ItemCharacterBinding
import com.example.marvel_characters.presentation.model.CharacterModel

class CharacterAdapter :
    ListAdapter<CharacterModel, CharacterAdapter.CharacterViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : ViewHolder(binding.root) {

        fun bind(model: CharacterModel) {
            binding.characterImage.networkLoad(model.imagePath, R.drawable.character_placeholder)
            binding.characterTitle.text = model.name
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<CharacterModel>() {
            override fun areItemsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean {
                return oldItem.imagePath == newItem.imagePath &&
                        oldItem.name == newItem.name
            }
        }
    }
}