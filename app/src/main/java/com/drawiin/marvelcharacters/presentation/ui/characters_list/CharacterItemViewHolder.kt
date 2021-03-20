package com.drawiin.marvelcharacters.presentation.ui.characters_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drawiin.marvelcharacters.databinding.CharacterItemBinding
import com.drawiin.marvelcharacters.domain.model.character.Character
import com.drawiin.marvelcharacters.utils.loadFromUrl

class CharacterItemViewHolder(
    private val binding: CharacterItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.title.text = character.name
        binding.description.text = character.description

        val basePath = character.thumbnail?.path
        val extension = "." + character.thumbnail?.extension
        val imageType = "portrait_incredible"

        binding.imagePoster.loadFromUrl("$basePath/$imageType$extension")
    }


    companion object {
        fun inflate(parent: ViewGroup) = CharacterItemViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}