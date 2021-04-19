package com.challenge.marvelcharacters.view.characters.adapters

import androidx.recyclerview.widget.RecyclerView
import com.challenge.marvelcharacters.databinding.CharactersDetailBinding
import com.challenge.marvelcharacters.model.Character

class  CharacterViewHolder(var binding : CharactersDetailBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(character: Character){
        binding.character = character
        binding.imgUrl = character.image.path+"."+character.image.extension
    }
}