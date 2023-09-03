package com.example.marvel_app.features.characters.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.features.characters.domain.model.Character
import com.example.marvel_app.databinding.ItemCharacterBinding
import com.example.marvel_app.framework.imageloader.ImageLoader
import com.example.marvel_app.utils.OnCharacterItemClick

class CharactersViewHolder(
    private val itemCharacterBinding: ItemCharacterBinding,
    private val imageLoader: ImageLoader,
    private val onItemClick: OnCharacterItemClick
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {

    private val textName = itemCharacterBinding.textName
    private val imageCharacters = itemCharacterBinding.imageCharacter

    fun bind(character: Character) {
        textName.text = character.name
        imageCharacters.transitionName = character.name
        imageLoader.load(imageCharacters, character.imageUrl)

        itemView.setOnClickListener {
            onItemClick.invoke(character, imageCharacters)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            imageLoader: ImageLoader,
            onItemClick: OnCharacterItemClick
        ): CharactersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCharacterBinding.inflate(inflater, parent, false)

            return CharactersViewHolder(itemBinding, imageLoader, onItemClick)
        }
    }
}