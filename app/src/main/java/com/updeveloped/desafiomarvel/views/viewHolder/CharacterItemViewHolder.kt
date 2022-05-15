package com.updeveloped.desafiomarvel.views.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.updeveloped.desafiomarvel.databinding.CharacterItemBinding
import com.updeveloped.desafiomarvel.helper.loadGlideUrl
import com.updeveloped.desafiomarvel.domain.entities.CharacterDetail

class CharacterItemViewHolder(private val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterDetail?) {
        if (character != null) {
            binding.title.text = character.name.verifyIsNullOrEmpty()
            binding.description.text = character.description.verifyIsNullOrEmpty()
        }
        val basePath = character?.thumbnail?.path
        val extension = "." + character?.thumbnail?.extension

        binding.imageCharacter.loadGlideUrl("${basePath}${extension}")
    }

    private fun String?.verifyIsNullOrEmpty(): String {
        return if(this.isNullOrEmpty()) "Nenhuma informação encontrada." else this
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