package com.ncz.desafio_mobile_android.app.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncz.desafio_mobile_android.databinding.CardHeroItemBinding
import com.ncz.desafio_mobile_android.domain.entities.character.Character
import com.squareup.picasso.Picasso

class HeroesAdapter(private val heroesList: List<Character>) :
    RecyclerView.Adapter<HeroesAdapter.ViewHolderHeroes>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHeroes {
        val binding =
            CardHeroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderHeroes(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderHeroes, position: Int) {
        holder.nameHero.text = heroesList[position].name

        val baseUrl = heroesList[position].thumbnail.let {
            "${it.path}/portrait_incredible.${it.extension}"
        }

        Picasso.get().load(baseUrl).into(holder.imageHero)
    }

    override fun getItemCount() = heroesList.size

    inner class ViewHolderHeroes(binding: CardHeroItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameHero = binding.txtNameHero
        val imageHero = binding.imgIcon
    }
}