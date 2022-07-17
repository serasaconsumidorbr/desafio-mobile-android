package com.ncz.desafio_mobile_android.app.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncz.desafio_mobile_android.databinding.CardCharacterItemBinding
import com.ncz.desafio_mobile_android.domain.entities.character.Character
import com.squareup.picasso.Picasso

class CharactersAdapter(private val characterList: List<Character>) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolderCharacter>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacter {
        val binding =
            CardCharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return ViewHolderCharacter(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderCharacter, position: Int) {
      holder.name.text = characterList[position].name
        holder.description.text = characterList[position].description
        val baseUrl = characterList[position].thumbnail.let {
            "${it.path}/portrait_fantastic.${it.extension}"
        }

        Picasso.get().load(baseUrl).into(holder.image)

    }

    override fun getItemCount() = characterList.size

    inner class ViewHolderCharacter(binding: CardCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.txtName
        val description = binding.txtDescription
        val image = binding.imgIcon
    }

}