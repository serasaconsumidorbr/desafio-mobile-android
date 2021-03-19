package com.drawiin.myheroes.presentation.ui.heroes_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drawiin.myheroes.databinding.CarouselItemBinding
import com.drawiin.myheroes.domain.model.character.Character
import com.drawiin.myheroes.utils.loadFromUrl

class CarouselItemViewHolder(
    private val binding: CarouselItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.title.text = character.name
        binding.description.text = character.description

        val basePath = character.thumbnail?.path
        val extension = "." + character.thumbnail?.extension

        binding
            .thumbnail
            .loadFromUrl("$basePath$extension")

    }


    companion object {
        fun inflate(parent: ViewGroup) = CarouselItemViewHolder(
            CarouselItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}